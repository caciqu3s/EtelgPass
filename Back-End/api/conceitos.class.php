<?php
include("aluno.class.php");
class Conceitos
{
  private $tabela_conceitos;
  function __construct($aluno)
  {
    if(is_string($aluno)){
      $aluno = new Aluno($aluno);
    }
    //URL da página de faltas
    $url = 'http://etec.educacao.ws/notas/faltamensal.asp';

    //dados do post da página
    $data = array('txtnum'=>$aluno->getNumero(),'txtano' => $aluno->getAno(),'txtserie' => $aluno->getSerie(),'txtturma' => $aluno->getTurma(),'txtcod' => $aluno->getId());
    //obtem o HTML
    $result = getHTML($url,$data);
    //ignora erros do HTML
    libxml_use_internal_errors(true);
    //Cria objeto para ler o HTML
    $document = new DOMDocument();
    $document->loadHTML($result);
    //bloqueia a exibição de erros no PHP
    libxml_clear_errors();
    //obtem a tabela
    $tabelas = $document->getElementsByTagName("table");
    //linhas da tabela
    $linhas = $tabelas->item(0)->getElementsByTagName("tr");
    $conceitos = array();
    //bool para pular a primeira linha(o cabeçalho)
    $pular = true;
    foreach ($linhas as $linha) {
      if($pular == true){
        $pular = false;
      }else {
        //obtem as colunas da linha
        $colunas = $linha->getElementsByTagName('td');
        //Para os conceitos, caso não sejam um traço, ou seja, contenham um conceito
        //cria um array com os conceitos e salva no array de conceitos
        $conceito = array(
          'mes' => $colunas[0]->nodeValue,
          'professor' => $colunas[1]->nodeValue,
          'disciplina' =>$colunas[2]->nodeValue,
          'aulas' =>$colunas[3]->nodeValue,
          'faltas' =>$colunas[4]->nodeValue,
          'conceito'=> $colunas[5]->nodeValue,
          'conceito_final' =>$colunas[6]->nodeValue);
          array_push($conceitos,$conceito);
        }
      }
      $this->tabela_conceitos = $conceitos;
    }

    public function porDisciplina(){
      $conceitos = $this->tabela_conceitos;
      //ordena por matéria
      usort($conceitos, "compararDisciplina");
      //serve para fazer um loop a mais no foreach (para salvar a última disciplina)
      $final = array(
        'mes' => '',
        'professor' => '',
        'disciplina' =>'',
        'aulas' =>'',
        'faltas' =>'',
        'conceito'=> '',
        'conceito_final' =>'');
        array_push($conceitos,$final);
        //return
        $resultado = array();

        //variaveis do loop
        $disc_atual = $conceitos[0]["disciplina"];
        $disc_aulas = 0;
        $disc_faltas = 0;

        $disc_conceitos_1 = "?";
        $disc_conceitos_2 = "?";
        $disc_conceitos_3 = "?";
        $disc_conceitos_4 = "?";
        $disc_conceito_final = "?";
        $disc_professor = "";
        $total_aulas = 0;
        $total_faltas = 0;
        foreach ($conceitos as $conceito) {
          //salva dados, passa para a próxima disciplina
          if($conceito["disciplina"] != $disc_atual){
            $d = array("disciplina" => $disc_atual, "aulas" => $disc_aulas, "faltas" => $disc_faltas, "conceitos" => array($disc_conceitos_1,$disc_conceitos_2,$disc_conceitos_3,$disc_conceitos_4),"conceito_final" => $disc_conceito_final,"professor" => $disc_professor);
            array_push($resultado,$d);
            $disc_conceitos_1 = "?";
            $disc_conceitos_2 = "?";
            $disc_conceitos_3 = "?";
            $disc_conceitos_4 = "?";
            $disc_conceito_final = "?";
            $disc_professor = "";
            $total_aulas += $disc_aulas;
            $total_faltas += $disc_faltas;
            $disc_faltas = 0;
            $disc_aulas = 0;
            $disc_atual = $conceito["disciplina"];
          }
          //conceitos
          if($conceito["conceito"] != "-"){
            $mes = str_replace(" ",'',$conceito["mes"]);
            switch ($mes) {
              case 'ABRIL':
              $disc_conceitos_1 = $conceito["conceito"];
              break;
              case 'JUNHOeJULHO':
              $disc_conceitos_2 = $conceito["conceito"];
              break;
              case 'SETEMBRO':
              $disc_professor = $conceito["professor"];
              $disc_conceitos_3 = $conceito["conceito"];
              break;
              case 'NOVEMBRO-DEZEMBRO':
              $disc_conceitos_4 = $conceito["conceito"];
              break;
            }
          }
          if($conceito["conceito_final"] != "-"){
            $disc_professor = $conceito["professor"];
            $disc_conceito_final = $conceito["conceito_final"];
          }

          //aulas e faltas
          $disc_aulas += (int)$conceito["aulas"];
          $disc_faltas += (int)$conceito["faltas"];
        }

        $indefinidas = 0;
        $reprovado = 0;
        $aprovado = 0;
        foreach ($resultado as $disciplina) {
          switch ($disciplina["conceito_final"]) {
            case '?':
            $indefinidas ++;
            break;
            case 'I':
            $reprovado++;
            break;
            case 'R':
            $aprovado++;
            break;
            case 'B':
            $aprovado++;
            break;
            case 'MB':
            $aprovado++;
            break;
          }
        }

        return array("disciplinas" =>$resultado, "aulas" => $total_aulas, "faltas"=> $total_faltas,"aprovado"=> $aprovado,"reprovado" => $reprovado,"indefinido"=>$indefinidas,"total" => count($resultado));
      }

    }

    function compararDisciplina($a, $b) {
      if ($a['disciplina'] == $b['disciplina']) {
        return 0;
      }
      return ($a['disciplina'] < $b['disciplina']) ? -1 : 1;
    }

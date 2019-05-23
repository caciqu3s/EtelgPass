<?php
include("funcoes.php");
class Aluno
{
  private $rm;
  private $nome;
  private $curso;
  private $serie;
  private $turma;
  private $numero;
  private $id;

  //método construtor da classe
  function __construct($rm)
  {
    //url da página de login
    $url = 'http://etec.educacao.ws/notas/formALUNO.asp';
    //dados do POST para a página de login
    $data = array('txtrm' => $rm);
    //salva o html da página
    $result = getHTML($url,$data);
    //não exibe erros do HTML da página do formALUNO
    libxml_use_internal_errors(true);
    $document = new DOMDocument();
    $document->loadHTML($result);
    //usado para não exibir os erros do HTML
    libxml_clear_errors();

    $inputs = $document->getElementsByTagName("input");

    foreach ($inputs as $input) {
      switch ($input->getAttribute("name")) {
        case 'txtcurso':
        $this->curso = $input->getAttribute("value");
        break;
        case 'txtano':
        $this->ano = $input->getAttribute("value");
        break;
        case 'txtnome':
        $this->nome = ucwords(strtolower($input->getAttribute("value")));
        break;
        case 'txtserie':
        $this->serie = $input->getAttribute("value");
        break;
        case 'txtturma':
        $this->turma = $input->getAttribute("value");
        break;
        case 'txtnum':
        $this->numero = $input->getAttribute("value");
        break;
        case 'txtcod':
        $this->id = $input->getAttribute("value");
        break;
      }
    }
  }
  public function getNome(){
    return $this->nome;
  }
  public function getRm(){
    return $this->rm;
  }
  public function getCurso(){
    return $this->curso;
  }
  public function getAno(){
    return $this->ano;
  }
  public function getSerie(){
    return $this->serie;
  }
  public function getTurma(){
    return $this->turma;
  }
  public function getNumero(){
    return $this->numero;
  }
  public function getId(){
    return $this->id;
  }
}

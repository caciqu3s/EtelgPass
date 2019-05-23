<?php include("api/conceitos.class.php");
include("conexao.php");
ini_set('display_errors', 0 );
error_reporting(0);
header("Content-Type: application/json; charset=UTF-8");
if(isset($_GET["rm"])){
	strtoupper($_GET["rm"]);
	$aluno = new Aluno($_GET["rm"]);
	$i = 0;
	$j = 0;
	$k = 0;
	$versao = "1.1";
	$porcentagem_soma = "";
	$erro = "";
	$total_aulas = 0;
	$total_faltas = 0;
	
	
	if($aluno->getNome()){
		$conceitos = new Conceitos($aluno);
		$resultado = $conceitos->porDisciplina();
		$total_faltas = $resultado["faltas"];
		$total_aulas = $resultado["aulas"];
		$porcentagem_geral = 100 - (($total_faltas *100)/$total_aulas);

		if($resultado["aulas"]<1){
			header("location:erro.html");
		}

		foreach ($resultado["disciplinas"] as $disciplina) {
			$porcentagem = ($disciplina["faltas"] *100)/$disciplina["aulas"];

			$porcentagem = 100 - $porcentagem;
			$porcentagem_soma += $porcentagem;
			$array[$i] = array("disciplina" => $disciplina["disciplina"], "professor" => $disciplina["professor"], 
			"conceito1" => $disciplina["conceitos"][0], "conceito2" => $disciplina["conceitos"][1], 
			"conceito3" => $disciplina["conceitos"][2], "conceito4" => $disciplina["conceitos"][3], 
			"conceito_final" => $disciplina["conceito_final"], "porcentagem"  => $disciplina["aulas"] . " / " . $disciplina["faltas"] . 
			" -> ". round($porcentagem,2));
			$i++;
		}

		$comando="select * from cardapiogov where date_format(Dia, '%Y-%m-%d') BETWEEN '2018-10-15' AND '2018-10-19' order by Dia asc";
    	$resulta = mysqli_query($con,$comando) or die("erro com select 1");

	    if(mysqli_num_rows($resulta) > 0)
	    {
	    	$array_gov = array();



	    	while($gov = mysqli_fetch_array($resulta))
	    	{

	    		$array_gov[$j] = array("Dia" => date("d-m-Y", strtotime($gov['Dia'])), "Opção 1" => nl2br($gov['Op1']), "Opção 2" => nl2br($gov['Op2']),  "Opção 3" => nl2br($gov['Op3']),  "Opção 4" => nl2br($gov['Op4']),  "Opção 5" => nl2br($gov['Op5']));
	    		
	    		$j++;
	    	}
	    }	

	    $comando="select * from cardapioApm where date_format(Dia, '%Y-%m-%d') BETWEEN '2018-10-01' AND '2018-10-05' order by Dia asc;";
    	$resulta = mysqli_query($con,$comando) or die("erro com select 2");

	    if(mysqli_num_rows($resulta) > 0)
	    {
	    	$array_apm = array();



	    	while($apm = mysqli_fetch_array($resulta))
	    	{

	    		$array_apm[$k] = array("Dia" => date("d-m-Y", strtotime($apm['Dia'])), "Prato Básico" => nl2br($apm['pBasico']), "Salada" => nl2br($apm['Salada']),  "Prato Principal" => nl2br($apm['pPrincipal']),  "Guarnição" => nl2br($apm['Guarnicao']),  "Sobremesa" => nl2br($apm['Sobremesa']));
	    		
	    		$k++;
	    	}
	    }	

		$array_aluno = array("Aluno" => array("Nome" => $aluno->getNome(), "Curso" => $aluno->getCurso(), 
		"Ano" => $aluno->getAno(), "Sala" => $aluno->getSerie() . "º" .$aluno->getTurma(),  
		"Número" => $aluno->getNumero(), "Versão" => $versao, "Porcentagem Geral" => $total_aulas .
		 " / " . $total_faltas  . " -> " . round($porcentagem_geral,2) ));
		$json = array($array_aluno, "Notas" => $array, "Gov" => $array_gov, "APM" => $array_apm, "Erro" => $erro);
		echo json_encode($json, JSON_UNESCAPED_SLASHES | JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT);
	}
	else {$erro = array("Erro" => "RM inválido");
	    $json = array($array_aluno, "Notas" => $array, "Gov" => $array_gov, "APM" => $array_apm, "Erro" => $erro);
		echo json_encode($json, JSON_UNESCAPED_SLASHES | JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT);}
}
else {$erro = array("Erro" => "RM inválido");
	    $json = array($array_aluno, "Notas" => $array, "Gov" => $array_gov, "APM" => $array_apm, "Erro" => $erro);
		echo json_encode($json, JSON_UNESCAPED_SLASHES | JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT);}
?>
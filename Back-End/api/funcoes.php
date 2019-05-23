<?php
//Função para obter o HTML da página, com parametros de url e dados(usando metodo POST)
function getHTML ($url,$data){
  $options = array(
      'http' => array(
          'header'  => "Content-type: application/x-www-form-urlencoded\r\n",
          'method'  => 'POST',
          'content' => http_build_query($data)
      )
  );
  $context  = stream_context_create($options);
  $result = file_get_contents($url, false, $context);

  if ($result === FALSE) {
    echo "<h1>ERRO</h1>";
    echo "<h3>Desculpe, parece que houve um erro no servidor. Por favor volte mais tarde.</h3>";
    die();
  }
  return $result;
}
?>

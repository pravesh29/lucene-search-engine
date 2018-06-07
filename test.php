<?php

$qs = $_POST['qs'];

exec("java -jar Search.jar \"$qs\" 2>&1", $outputAndErrors, $return_value);
?>

<div class="table-responsive">
  <table class="table">
    <thead>
      <tr>
        
        <th>Document Id</th>
        <th>Document Text</th>
      </tr>
    </thead>
    <tbody>
<?
foreach ($outputAndErrors as $key => $value) {
      	# code... 
		$split = explode("//////", $value)
        ?>
        <tr>
        <td><?=$split[0] ?></td>
        <td><?=$split[1] ?></td>
      </tr>
     <? } ?> 
    </tbody>
  </table>
  </div>
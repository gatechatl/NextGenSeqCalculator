  <?php

$size = $_GET["size"];
$sample = $_GET["sample"];
exec("java CreateCSVFile sequencer_meta.txt " . $size . " " . $sample);
//exec("");
     /*$file = fopen("file", "w");
     fwrite($file, $_GET["size"]);
     fclose($file);*/

  ?>


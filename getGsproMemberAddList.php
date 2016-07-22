<?php
require_once( 'lib/config.php');
require_once("class/Db_obj.php");

$db = new Db_obj();

$status = 0;
//if (strtoupper($_SERVER['REQUEST_METHOD']) == "GET") {
if (strtoupper($_SERVER['REQUEST_METHOD']) == "POST") {
	$name = mysql_real_escape_string($_POST["name"]);
	$sns_user_id = mysql_real_escape_string($_POST["sns_user_id"]);
	$sex = mysql_real_escape_string($_POST["sex"]);

	$sql = "insert into gsProMember (sns_user_id,name,sex) values('".$sns_user_id."','".$name."',".$sex.")";
	$_info = array();
	if ( $_info = $db->insertSQL($sql)) {
		$status = 1;
	}
	$responce['status'] = $status;
	$responce['sql'] = $sql;
	echo json_encode($responce);
}

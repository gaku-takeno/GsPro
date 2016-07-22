<?php
require_once( 'lib/config.php');
require_once("class/Db_obj.php");

$db = new Db_obj();

$status = 0;
if (strtoupper($_SERVER['REQUEST_METHOD']) == "POST") {
	$name = mysql_real_escape_string($_POST["name"]);
	$sns_user_id = mysql_real_escape_string($_POST["sns_user_id"]);
	$sex = mysql_real_escape_string($_POST["sex"]);
	$message = mysql_real_escape_string($_POST["message"]);

	$sql = "update gsProMember set name = '".$name."', sex = ".$sex." ,message = '".$message."' where sns_user_id = '".$sns_user_id."'";
	$_info = array();
	if ( $_info = $db->updateSQL($sql)) {
		$status = 1;
	}
	$responce['status'] = $status;
	$responce['sql'] = $sql;
	echo json_encode($responce);
}

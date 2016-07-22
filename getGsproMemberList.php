<?php
require_once( 'lib/config.php');
require_once("class/Db_obj.php");

$db = new Db_obj();

if (strtoupper($_SERVER['REQUEST_METHOD']) == "GET") {
	//if (strtoupper($_SERVER['REQUEST_METHOD']) == "POST") {
	if ($_GET['page'] == 'r98b$wk221oob') {
		//$current_page = mysql_real_escape_string($_POST["current_page"]);
		$sql = "SELECT * from gsProMember";
		$_info = array();
		if ( $_info = $db->selectSQL_Assoc($sql)) {
		}
		$responce['gsmembers'] = $_info;
		echo json_encode($responce);
	}
}

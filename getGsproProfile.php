<?php
require_once( 'lib/config.php');
require_once("class/Db_obj.php");

$db = new Db_obj();
if (strtoupper($_SERVER['REQUEST_METHOD']) == "POST") {
	if ($_POST['page'] == 'r98b$wk221oob') {
		$sns_user_id = mysql_real_escape_string($_POST["sns_user_id"]);
		$sql = "SELECT * from gsProMember where sns_user_id =".$sns_user_id;
		$_info = array();
		if ( $_info = $db->selectSQL_Assoc($sql)) {
			$responce['gsmembers'] = $_info[0];
		}
		echo json_encode($responce);
	}
}

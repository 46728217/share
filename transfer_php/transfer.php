<?php
set_time_limit(-1);
$current_count = 0;
while(true) {
  $count = func1($current_count, 100);
  if (!$count)
	  break;
  $current_count += $count;
}


function func1 ($start, $limit) {
	$conn = @mysql_connect("192.168.33.10", "root", "123.abc");
	if (!$conn) die;
	$db_selected = mysql_select_db("db_transfer", $conn);
	$sql = "select id, year_area from cars limit %d, 20";
	$result = mysql_query(sprintf($sql, $start), $conn);
	$count = 0;
	while($row = mysql_fetch_array($result, MYSQL_ASSOC)) {
		$count++;
		if (preg_match('/^(\d{4}\.\d{2})?-(\d{4}\.\d{2})?$/i', $row['year_area'])){
			$arr = explode('-', $row['year_area']);
			$start_year = $arr[0];
			$end_year = $arr[1];
			if ($start_year) {
				$start_year .= '.01';
				$start_year = str_replace('.', '-', $start_year);
			}else
				$start_year = '1970-01-01';
            if ($end_year) {
				$end_year .= '.01';
				$end_year = str_replace('.', '-', $end_year);
			}else
				$end_year = '2014-12-31';
			$sql = "update cars set start_year = '".$start_year."',end_year = '".$end_year."' where id = " . $row['id'];
			mysql_query($sql, $conn);
		}
	}
	mysql_close($conn);
	return $count;
}

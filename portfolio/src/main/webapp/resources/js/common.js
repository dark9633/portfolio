function javaDateFormat(date){
	var result;
	
	var dateObj = new Date(date);
	var year = dateObj.getFullYear();
	var month = dateObj.getMonth() + 1;
	var date = dateObj.getDate();
	
	if( month < 10){
		month = "0" + month;
	}
	if( date < 10){
		date = "0" + date;
	}
	result = year + "-" + month + "-" + date;
	return result;
}
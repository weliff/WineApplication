$(function() {
	var settings = {
		type:'json',
		filelimit:1,
		allow:'*.(jpeg|jpg|png)',
	};
	
	UIkit.uploadSelect($('#upload-select'), settings);
	UIkit.uploadDrop($('#upload-drop'), settings);
});
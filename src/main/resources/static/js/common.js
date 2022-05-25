/**
 * 
 */
 
 let dispmenu = true;
 
 function click_hanbergar(){
	let menu = document.getElementById("menu");
	
	menu.style.display = (dispmenu) ? "block" : "none";
	dispmenu = !dispmenu;
}
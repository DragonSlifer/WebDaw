//Javascript

function invokeScript(divid)
{
	var scriptObj = divid.getElementsByTagName("SCRIPT");
	var len = scriptObj.length;
	for(var i=0; i<len; i++)
	{
		var scriptText = scriptObj[i].text;
		var scriptFile = scriptObj[i].src;
		var scriptTag = document.createElement("SCRIPT");
		if ((scriptFile != null) && (scriptFile != "")){
			scriptTag.src = scriptFile;
		}
		scriptTag.text = scriptText;
		if (!document.getElementsByTagName("HEAD")[0]) {
			document.createElement("HEAD").appendChild(scriptTag);
		}
		else {
			document.getElementsByTagName("HEAD")[0].appendChild(scriptTag);
		}
	}
}
                        
function nuevaConexion()
{
	var xmlhttp=false;
	try {
		xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
	}
	catch (e)
	{
		try {
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		} 
		catch (E)
		{ 
			xmlhttp = false;
		}
	}

	if (!xmlhttp && typeof XMLHttpRequest!='undefined')
	{
		xmlhttp = new XMLHttpRequest();
	}
	return xmlhttp; 
}

function Cargar(url, capa)
{
	var contenido = document.getElementById(capa);
	var conexion = nuevaConexion();
	conexion.open("GET", url, true);
	conexion.onreadystatechange=function()
	{ 
		if(conexion.readyState == 4)
		{
			contenido.innerHTML = conexion.responseText;
			invokeScript(document.getElementById(capa));
		}
	}
	conexion.send(null);                               
} 
/**
 * CargarForm(url, capa, valores).
 * Esta función carga una capa y puede llevar unos valores.
 * 
 * @param {type} url
 * @param {type} capa
 * @param {type} valores
 * @returns {undefined}
 */
function CargarForm(url, capa, valores)
{
	var contenido = document.getElementById(capa);
        
	var conexion = nuevaConexion();
	conexion.open("POST", url, true);
	conexion.onreadystatechange=function()
		{ 
			if(conexion.readyState == 4)
			{
				contenido.innerHTML = conexion.responseText;
				invokeScript(document.getElementById(capa));
			}
		};
	conexion.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
	conexion.send(valores);
} 

/**
 * ProcesarForm(formulario,url,capa).
 * Esta función guarda los valores de un formulario y lo envia de nuevo a la capa
 * para que esta los tenga disponibles.
 * 
 * @param {type} formulario
 * @param {type} url
 * @param {type} capa
 * @returns {undefined}
 */
function ProcesarForm(formulario, url, capa)
{
	var valores="";
	for (i=0; i<formulario.elements.length;i++)
	{
		var nombre = formulario.elements[i].name;
		if (nombre!=="")
		{
			if (!((formulario.elements[i].type === "radio") && (!formulario.elements[i].checked)))
			{
				valores += formulario.elements[i].name + "=";
				valores += formulario.elements[i].value + "&";
                                
			}
		}
	}
	CargarForm(url, capa, valores);
}
			
function cargaInicial()
{                            
	Cargar('menu.html','menu');
	Cargar('inicial.html','contenido');
}

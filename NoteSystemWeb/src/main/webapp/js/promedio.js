function agregarValores() {
	let num1 = parseFloat(document.getElementById('form:txt1').value);
	let num2 = parseFloat(document.getElementById('form:txt2').value);
	let num3 = parseFloat(document.getElementById('form:txt3').value);

	let promedio = calcularPromedio(num1, num2, num3);

	document.getElementById('form:txt4').value = promedio;

	console.log('El promedio es:', promedio);
}

function calcularPromedio(num1, num2, num3) {
	// Verificar si los argumentos son números
	if (typeof num1 !== 'number' || typeof num2 !== 'number'
		|| typeof num3 !== 'number') {
		throw new Error('Todos los argumentos deben ser números.');
	}

	// Calcular el promedio
	let promedio = (num1 + num2 + num3) / 3;

	return promedio;
}
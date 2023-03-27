// Inicializa un array vacío para el carrito
let carrito = [];

// Función para obtener el carrito desde el almacenamiento local
function obtenerCarrito() {
  // Obtiene el carrito almacenado en el localStorage
  var carrito = localStorage.getItem("carrito");
  // Si no hay un carrito almacenado, devuelve un array vacío
  if (carrito === null) {
    return [];
  } else {
    // Si hay un carrito almacenado, lo parsea y lo devuelve como un array
    return JSON.parse(carrito);
  }
}

// Función para guardar el carrito en el almacenamiento local
function guardarCarrito(carrito) {
  // Guarda el carrito en el localStorage como una cadena JSON
  localStorage.setItem("carrito", JSON.stringify(carrito));
}

// Función para agregar un producto al carrito
function agregarProducto(codigo, descripcion, existencias, precio) {
  // Obtiene el carrito actual
  var carrito = obtenerCarrito();
  // Inicializa una variable para saber si el producto ya está en el carrito
  var productoEncontrado = false;

  // Recorre el carrito para buscar el producto
  for (var i = 0; i < carrito.length; i++) {
    // Si encuentra el producto, aumenta su cantidad y disminuye su existencia
    if (carrito[i].codigo === codigo) {
      if (carrito[i].existencias > 0) {
        carrito[i].cantidad++;
        carrito[i].existencias--;
        productoEncontrado = true;
        break;
      } else {
        // Si no hay existencias, muestra un mensaje de alerta y sale de la función
        alert("No hay más existencias de este producto.");
        return;
      }
    }
  }

  // Si no encontró el producto, lo agrega al carrito
  if (!productoEncontrado) {
    if (existencias > 0) {
      carrito.push({
        codigo: codigo,
        descripcion: descripcion,
        precio: precio,
        cantidad: 1,
        existencias: existencias - 1,
      });
    } else {
      // Si no hay existencias, muestra un mensaje de alerta
      alert("No hay más existencias de este producto.");
    }
  }

  // Guarda el carrito en el localStorage y lo muestra en la página
  guardarCarrito(carrito);
  mostrarCarrito(carrito);
  console.log(carrito);
}

// Función que elimina un producto del carrito por su código
function eliminarProducto(codigo) {
  var carrito = obtenerCarrito(); // Obtiene el carrito desde el almacenamiento local
  var productoEncontrado = false;

  // Recorre todos los productos del carrito
  for (var i = 0; i < carrito.length; i++) {
    // Si encuentra un producto con el mismo código que el que se quiere eliminar
    if (carrito[i].codigo === codigo) {
      carrito.splice(i, 1); // Lo elimina del carrito
      productoEncontrado = true;
      break;
    }
  }

  // Si no encontró el producto que se quería eliminar, muestra un mensaje de alerta
  if (!productoEncontrado) {
    alert(
      "No se puede eliminar el producto, ya que no ha sido añadido al carrito."
    );
  } else { // Si lo encontró
    guardarCarrito(carrito); // Guarda el carrito actualizado en el almacenamiento local
    mostrarCarrito(carrito); // Muestra el carrito actualizado
  }

  console.log(carrito); // Imprime el carrito actualizado en la consola
}

// Función que vacía todo el carrito
function vaciarCarrito() {
  localStorage.removeItem("carrito"); // Borra el carrito del almacenamiento local
  mostrarCarrito(carrito); // Muestra el carrito vacío
  console.log(carrito); // Imprime el carrito vacío en la consola
}


// Función que muestra el contenido del carrito en una tabla HTML
function mostrarCarrito() {
  let carrito = obtenerCarrito();
  let tablaCarrito = document.getElementById("tablaCarrito");
  let totalGeneral = 0;
  let totalCantidad = 0;

  // Elimina las filas existentes de la tabla
  while (tablaCarrito.rows.length > 1) {
    tablaCarrito.deleteRow(1);
  }

  // Itera sobre los productos en el carrito
  for (let producto of carrito) {
    let totalProducto = producto.precio * producto.cantidad;
    let cantidadProducto = producto.cantidad;
    
    totalGeneral += totalProducto;
    totalCantidad += cantidadProducto;
    
    let fila = document.createElement("tr");
    fila.innerHTML = `
      <td><img src="./images/${producto.codigo}.jpg" alt="${producto.descripcion}"></td>
      <td>${producto.descripcion}</td>
      <td>${producto.precio} &euro;</td>
      <td>${producto.cantidad}</td>
      <td><input type="button" class="button1" value="Eliminar" onclick="eliminarProducto('${producto.codigo}')"></td>`;
      
    tablaCarrito.appendChild(fila);
  }

  // Agrega una fila con el total general del carrito
  let filaTotal = document.createElement("tr");
  filaTotal.innerHTML = `
  	<td>&nbsp;</td>
    <td colspan="1"><b>Total</b></td>
    <td><b>${totalGeneral} &euro;<b></td>
    <td><b>${totalCantidad}<b></td>
    <td><input type="button" class="button1" value="Vaciar carrito" onclick="vaciarCarrito()"></td>`;
    
  tablaCarrito.appendChild(filaTotal);
}

// Función que muestra el contenido del pedido en una tabla HTML
function mostrarPedido() {
  let carrito = obtenerCarrito();
  let tablaCarrito = document.getElementById("tablaPedido");
  let totalGeneral = 0;
  let totalCantidad = 0;

  // Elimina las filas existentes de la tabla
  while (tablaCarrito.rows.length > 1) {
    tablaCarrito.deleteRow(1);
  }

  // Itera sobre los productos en el carrito
  for (let producto of carrito) {
    let totalProducto = producto.precio * producto.cantidad;
    let cantidadProducto = producto.cantidad;
    
    totalGeneral += totalProducto;
    totalCantidad += cantidadProducto;
    
    let fila = document.createElement("tr");
    fila.innerHTML = `
		<td>${producto.codigo}</td>
		<td>${producto.descripcion}</td>
		<td>${producto.precio} &euro;</td>
		<td>${producto.cantidad}</td>`;
      
    tablaCarrito.appendChild(fila);
  }

  // Agrega una fila con el total general del carrito
  let filaTotal = document.createElement("tr");
  filaTotal.innerHTML = `
    <td colspan="2"><b>Total</b></td>
    <td><b>${totalGeneral} &euro;<b></td>
    <td><b>${totalCantidad}<b></td>`;
    
  tablaCarrito.appendChild(filaTotal);
}
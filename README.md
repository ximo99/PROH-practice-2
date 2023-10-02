
Asignatura de Programación Hipermedia dentro del grado de Ingeniería Multimedia por la ETSE (Escuela Técnica Superior de Enginiería) - Universitat de València. Abril - mayo 2023. Práctica 2. Calificación 8,5.

El objetivo de esta práctica es continuar con la creación de la tienda virtual iniciada en la primera práctica. Para ello crearemos una aplicación web que siga el patrón de diseño Modelo/Vista/Controlador (MVC) e implemente en Java la parte dinámica de la tienda. En esta práctica proporcionaremos el contenido dinámico a las páginas web elaboradas en la
primera práctica, añadiendo un conjunto de Servlets, JavaBeans, Factories y páginas JSPs interconectados para permitir realizar la compra de los productos de la tienda virtual. Como parte de la arquitectura de la aplicación web, se deberá diseñar y crear una base de datos MySQL y una estructura de datos básica para almacenar la información relativa al sitio web creado.
La aplicación a desarrollar debe tener las siguientes características:
  - Dispondrá de un carrito de la compra donde los usuarios podrán ir añadiendo los productos que deseen, no siendo necesario que el usuario se encuentre autenticado para poder añadir productos al carrito de la compra, modificar los productos del carrito de la compra, etc.
  - Para finalizar el pedido, el usuario tendrá que autenticarse o estar autenticado con anterioridad en el sistema. La autenticación se realizará mediante una validación de usuario/contraseña.
  - Para autenticarse, el usuario deberá estar previamente registrado en el sistema. Para ello se habilitarán los formularios necesarios para realizar el registro del usuario, almacenando la información en la base de datos. Entre los datos del usuario deberán figurar obligatoriamente el nombre de usuario (que debe ser único) y la contraseña con los que podrá acceder a la aplicación.
 - La información de los pedidos realizados por cada usuario se almacenará en la base de datos, incluyendo el detalle de cada pedido. Los pedidos podrán estar en cuatro estados:
    - Pendiente: El pedido aún no se ha enviado.
    - Cancelado: El pedido ha sido cancelado por el usuario antes de su envío.
    - Enviado: El pedido ya ha sido enviado y no puede ser cancelado.
    - Entregado: El pedido ya ha sido entregado al usuario y todo el proceso ha finalizado.
  - El usuario dispondrá de una zona personal para, una vez autenticado, administrar los datos introducidos, ver sus pedidos, etc., pudiendo el usuario modificar sus datos personales, la contraseña de acceso, etc., excepto el nombre de usuario. Además podrá cancelar los pedidos que haya realizado y que se encuentren en estado de pendiente.

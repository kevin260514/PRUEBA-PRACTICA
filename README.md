Monolítico por Capas — Sistema de Ventas

¿Qué es la arquitectura monolítica por capas?
Es una arquitectura donde todo el sistema vive en un único bloque 
(monolítico), pero el código está organizado en capas con 
responsabilidades distintas. Cada capa solo se comunica con 
la capa inmediatamente inferior.

Las 4 capas del sistema
Presentacion.- Menu de entrada y salida del usuario
Servicio.- Reglas de negocio
Repositorio.- Acceso a datos en este caso en memoria
Modelo.-Entidades del negocio

ESTRUCTURA
capas/
├── Main.java
├── modelo/
│   ├── Producto.java
│   ├── Cliente.java
│   └── Venta.java
├── repositorio/
│   ├── ProductoRepositorio.java
│   ├── ClienteRepositorio.java
│   └── VentaRepositorio.java
├── servicio/
│   ├── InventarioServicio.java
│   └── VentaServicio.java
└── presentacion/
    └── Menu.java

    Funcionalidades
Registrar producto - Alta de artículos con nombre, precio y stock inicial
Registrar cliente - Incorporación de clientes al sistema
Realizar venta - Transacciones que actualizan automáticamente el stock
Ver stock - Consulta del inventario actual disponible
Reporte de ventas - Resumen consolidado de todas las transacciones

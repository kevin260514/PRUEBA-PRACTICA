Código Espagueti — Sistema de Ventas
¿Qué es?
El código espagueti es un estilo donde toda la lógica del sistema vive en un solo lugar, sin organización. El flujo es tan enredado como un plato de espaguetis: difícil de seguir, mantener y escalar.

Estructura
SistemaVentas.java: TODO en un solo archivo
Problemas
Problema	Consecuencia
Todo en main()	Un método hace absolutamente todo
Listas + lógica + menú mezclados	Imposible separar responsabilidades
Sin capas ni organización	Agregar una función rompe todo

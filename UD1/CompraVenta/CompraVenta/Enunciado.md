# Juego: "Compra y venta de artículos"

## Objetivo del juego:

Varios jugadores (procesos) compiten por comprar y vender artículos almacenados en un archivo compartido. 
Los artículos tienen un precio fijo y los jugadores pueden comprar o vender estos artículos, modificando 
la cantidad disponible de cada uno en el archivo. Sin embargo, deben sincronizar su acceso al archivo para 
evitar que dos jugadores intenten modificar la cantidad del mismo artículo al mismo tiempo.


## Reglas del juego:

- El archivo compartido contiene una lista de artículos con su cantidad disponible y un precio fijo. 
Ejemplo: "Artículo 1: 5 unidades, precio 10".
- Los jugadores (procesos) pueden comprar artículos (restando 1 unidad de la cantidad disponible) o vender 
artículos (añadiendo 1 unidad a la cantidad disponible), dependiendo de una acción aleatoria que decidan 
hacer.
- Los jugadores no pueden comprar más artículos de los que hay disponibles.
- Se escribirá por consola cada transacción (compra o venta) que haga un jugador.


## Estructura del proyecto:

1. Proceso principal (inicializa el juego y lanza los jugadores): Este proceso inicializa el archivo con varios 
artículos (cada uno con una cantidad y un precio) y lanza varios procesos concurrentes (jugadores) que intentan 
comprar y vender artículos.
2. Jugador (proceso concurrente que compra o vende artículos): Cada jugador toma una acción aleatoria (comprar
o vender), actualiza el archivo en consecuencia, y escribe la transacción por consola.
# Port-ship-shortest-path
Shortest path to find one port/dock to another port using Dijkstra Algorithm and Google Maps API to draw the graph. 

## Main feature
The program basically will find the shortest path by utilizing Dijkstra Algorithm. This program using latitude and longitude input to
calculate the distance in KM using Havensin theorm. After get calculated by Dijkstra, the program will show the result in a Google Maps.

## How it works
This program have 2 sides: back-end side by using Java and client-side (to display the map) by using Javascript. To create the bridge
between Java object and Javascript object, I use JXbrowser library. The Java side will calculate the distance and possible shortest path,
and the Javascript side will show the maps by retrieving Java object and send request to Google Maps API.

## External library
[JXBrowser](https://jxbrowser-support.teamdev.com/)

## Final stage
The program has fully implemented the backend and frontend **BUT** It is not fully stable yet due to I am not focusing on using
Java Thread Implementation.

## What needs to be added
1. The multithreaded feature to make program more stable.
2. Using exception to give signal if there is something wrong with the program.
  
 

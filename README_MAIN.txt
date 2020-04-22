AUTHORS
Muhammad Sulton Tauhid
Email	    : msulton55@gmail.com
Instagram   : msultont
Whatsapp	: +6282299024212
 
Shortest Path Finding Using Dijkstra Algorithm - 1.0

This application purpose's is to find the shortest way from one dock to other.
This application will show the result in 2 way:
    a. path list
    b. map overview (still in development)

This section is to show HOW TO RUN THIS APP:
1) javac *.java

2) java Map "graph_list\KOORDINAT PELABUHAN.txt" --directions <FROM> <TO>
<FROM> -> input source location you desire
<to>   -> input destination location you desire

3) java Map "graph_list\KOORDINAT PELABUHAN.txt" --show --directions <FROM> <TO>
this number 3 will run the app and show the map.

################ HOW TO UPDATE YOUR VERTICES AND EDGES IN GRAPH #################

To update edges and vertices, you must add through KOORDINAT PELABUHAN.txt file inside graph_list folder.
There is some code you must understand:
	1) Code with "i" indicates vertices. So you add the location as well as the latitude and longitude
	2) Code with "r" indicates edges. So you add the edges from the vertices you have added.


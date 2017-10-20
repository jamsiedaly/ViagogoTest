# ViagogoTest
My source code for the Viagogo take home test.

## Build
To build and run simply import the project into intelliJ and select run on Main.main().

##Questions

### How might you change your program if you needed to support multiple events at the
same location?  

I would store the event objects in an array on each location.

### How would you change your program if you were working with a much larger world
size?

Scaling the program would be a much more difficult question, I would probably use something like A* search forn finding events on
more complex maps where distance is not always so straight forward to compute. I would also partition maps in different zones and
limit the search space to the users zone at any given time. 


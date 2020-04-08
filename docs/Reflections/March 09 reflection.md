Reflection for each member 09 March

How we did as a group:
A few of us met up on Tuesday for a few hours and got a lot of work done. Meeting up to work
on the project together was very productive and it might be a good idea to do this more often,
as sitting next to each other greatly improved communication.
Towards the end we ran into some  issues with branch merging, namely that some branches we were
working on were behind the others and that led to a lot of merge conflicts that had to be manualy
solved.As such, starting this week we are going to use a new branch for each and every feature
 (e.g. BikeRentingScreen, FoodOrderingScreen etc.)


Individual reflections:

- Areti
This week I worked on the authentication together with Floris and got the application to
let users log in when they enter a correct username and password combination that is in the
database. There was no security involved, however I now understand the backend a lot better
and know how to access data from the database. I also helped fix some minor issues.

- Floris
This week I worked on the authentication in general. I made the registration opions and all the checks
for registration. We did not implement the registration and login properly initially, so I had to remake
the method which we created to change it from sending information to the clientside to sending information
to server side and checking if everything works from there. I Also made the user types give different
functionality (or at least, gave an early implementation for it) by implementing simple checks in different
pages.

- Horia
This week I have contributed much less than I would have liked to. At first I tried to implement
the room reservation screen logic but ran into issues with the server communication. During the
Tuesday meeting I saw and managed to pin down the issue with some JSON parsing client-side. Then
I came up with a new RoomReservationScreen that used different JavaFX controls, but it wasn't well designed yey.
Fortunately, Peter was quick to come up with a working FXML file. This week I am going to dedicate much more time
to the project

- Hristo
This week I worked on both client-side and server-side. I refactored the data model so it can be
better matched to the cleint-side. I did the room reservation controllers and for available timeslots.
The communcation between server and client was a bit tricky but I managed to do it.
So finally we have the roomreservation view working and getting the right information from database.

- Luc
I worked on getting room reservations working, I refactored the client side models with
constructors that had id so that we could make use of the roomreservationrequestmodel, and 
implimented methods in the reservation screen so that a reservation could be added in a certain
room on a certain date, but at the time with hardcoded user and timeslot. With the new UI that 
functionality needs to be re-implimented

- Milo
I worked on Junit testing this week for the server side controllers although not done I have made 
progress and have been doing more work than in previous weeks. I have attempted to use mocking to
emulate the repository/database using spring therefore making testing much more reliable. In the 
next coming weeks I will make more logically sized issues on the issue board regarding testing and 
finish the serverside controllers and entities.

- Peter
This week I made displaying the list of available rooms on the reservation screen dynamic.
I also made timeslot and date selection screen for the rooms and implementented logic for it
and made it ready for Horia to implement the database connection.

Concrete Actionables:
    -More meetings as a group to code and plan
    -More closely follow scrum (i.e. dont let branches live too long, as it caused massive conflicts in the repository)
    
TA Feedback Summary, Actionables:
 - Review merge requests properly
    - Decide for review procedure
    - Minimum reviews
    - Actually reviewing code
 - Proper agenda made and everyone looks at it and contributes to it
 - Assign every issue
 - Standard commit template 
    - Commits should refer to issues
    - https://chris.beams.io/posts/git-commit/
 - Follow SCRUM structure better with more frequent merging
 - All individual retrospectives must be complete
 - A group retrospective should be made as a whole (suggestion: this could be a retrospective if we add a summary at the end)
 - Concrete actionables for retrospective more clearly outlined, person and as a group. 
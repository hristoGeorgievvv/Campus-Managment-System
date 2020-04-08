# Reflection 23 March

How we did as a group:
We added some important functionalities this week to project and make some things running faster
and more smoothly.

How we improved compared to last week:
We used gitlab documentation a bit more which is really helpful.

What we learned this week:
We learned that commenting on merge requests can be useful.

How we can improve next week:
We can maybe make a meeting during the week to check our progress and discuss problems.


Individual reflections:
---

### Areti
This week I made a small change to the admin screen and worked on the food along with Horia. I worked
on the UI and on some methods on the client side and on the server side. I also added the foodVendor
entity to the database, but it still needs a connection to foodMenu.

### Floris
This week I worked on optimizing the server and client interactions with the server. My original idea of
creating a session was not possible using a rest API so instead I had to reduce the amount of times the
client sends and recieves information with the server. This has drastically improved the performance of our
room reservation screen (even though it is still not remarkably quick), and to improve the performance of
the server, I changed the hashing method to using a bcrypt encoder. It should be slightly faster than the
previous implementation. Afterward I wanted to work on the bike reservation or food ordering, but was not
able to properly communicate with my group on what I could do specifically. I would like to work on finishing
or fixing the bike reservation and food ordering next week, along with creating a draft for the report.

### Horia
This week I worked on small changes to the StudentScreen and started working on the Food UI with Areti. I had to think 
of some restrictions on the database to better implement the mockup of the screen, and the following week I will start adding 
functionalities to the Food UI.


### Hristo
This week I worked on bikereservation screen. I added controllers on client and server side,
refactored a bit the bikereservation screen but there is still work to be done on it.
I also added some entities about food but we should discuss them as a group.

### Luc
This week I pivoted from backend method implimentation to clientside unit-testing alongside Milo. I implimented tests
for half of the client-side models, along with the hasher class and ServerCommunication class. Servercommunication, along 
with the rest of the direct communication controllers, would require mocking to fully test, but all that would do is 
simulate a succesful response serverside, which has been tested there already, and so it is adequate to test the remaining
logic of the methods assuming the server is offline, which covers most of the code anyway. I also helped some teammates
with git issues, as things were acting up and not behaving as expected. As we close in on the end of the project, I want to
make sure I can be of help wherever possible, and we can smoothly transition into creating the report and video to finish 
things off.

### Milo
This week I continued unit-testing on the client-side this time, with help from Luc. I tested half of the Models namely;
RoomReservation, Timeslots and User, and tested some controllers; BuildingController, RoomeReservationController 
and TimeSlotsController. Unfortunately most of the methods in these controllers have HTTPRequests to the serverside, which
would require mocking to test, that we have not implemented. Therefore I could only test Failing or invalid HTTPResponses
mapping to non-existent methods, or requesting non-existent data. This test works both when the server is off or online.
Next week I will either continue testing or start work on the report/presentation.

### Peter
This week I continued on working on the UI. I've adjusted some things according to the client TA's and the TA's feedback.
Room filtering is now not in the separate window but built in the room explorer. The map view can now be opened on the room
screen. I've also provided some small fixes to the other screens.


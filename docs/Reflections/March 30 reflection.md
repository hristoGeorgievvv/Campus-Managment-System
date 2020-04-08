# Reflection 30 March

### How we did as a group: 
We worked together on the report and divided the sections as would best fit everyone. 
Everyone took initiative in doing their own section of the report and also helping each other.
As we get closer to the end of the project we feel more as a one team and can ask more for help.

### How we improved compared to last week:
We did more online meetings and updated what have done during the week.

### What we learned this week:
That we can do some online meetings during the week and help each other with certain issues.

### How we can improve next week:
We should start working on the video and take care of some final small issues on the project.

Individual reflections:
---

### Areti
This week I worked on the client server communication of the food feature and tried to get the food 
orders to be sent to the server and get put into the database. I wasn't able to finish and will 
continue to work on that this week. 

### Floris
This week I worked on general improvements to prevent security issues, and added different functionality
to different user types. The username of the current user is no longer stored in client side, and is instead
taken from the encrypted header which is stored client side. This means that a malicious user cannot easily 
change the username and access another person's account without actually knowing their full username and password,
as they would be unauthenticated if they tried to change only the username. Staffmembers and administrators can now 
see if reservations are reserved from students and they can override it if they want to. The student and staff
info pages have also been updated. I worked a bit on the report draft, mostly on security and the areas I 
worked on but I will be working more on improving the report going forward. I am happy with what I managed
to achieve this week but I want to work more on the report and ensuring that we end the project well.

### Horia
This week I finished the main parts of the FoodScreen client-side logic. I worked with Luc to get the back-end working
properly. On the report side of the project, I introduced my teammates to how Overleaf works, and I tried to style it as
nicely as possible, removing the unnecessary parts of the template (e.g. DOI, publication date, copyright). This week, I want
to keep working on the finishing touches for the Food screen, such as styling and adding proper server communication, as well as the 
report

### Hristo
This week, I worked on a feature that a user can't book a rook in specific days (holidays). I met some trouble when sending dates
from server to the client side and parsing them. Also I worked on the report draft. I did the the sectios on database and a feature
of our general architecture. 


### Luc
This week, I worked mostly on the report draft. I did the sections on gitlab, group communication,
and the section on responsible CS. Code wise, I worked with Horia to get the backend working for 
food, and did general bugfixing and investigation into why certain parts of the program were behaving
weirdly; such as the user getting deleted if they tried to make a bike reservation while having a room
reservation, turned out to be some issues with the methods involved with changing the user bike value. 
As we get closer to the end of the project, I want to make sure I am a good team member, working as well
as I can, and helping to close off this project on a good note.

### Milo
This week I worked primarily on the draft report. I made all the issues for the draft in referance to the
rubric but sadly I did not take into consideration of the message from the TAs as I did not know it was there 
at the time. Therefore the structure and some content of the rubric will need to be changed. I also wrote the 
introduction, a paragraph on Scrum, a paragraph on Testing, and a paragraph on the general architecture of the
program. Additionally I continued working on the client side controller tests, leaving 2 more to be done. This
next week I will finish client side testing and add all the tests for any additional methods to the server-side
that have not been tested yet, aswell as working on fixing the report to go in line more with what the TAs asked
for and working on the presentations. 

### Peter
This week I worked on imroving the UI. I styled and added some functionality to the bike renting view that I
inherited from Hristo. I was also working on fixing some UI bugs on the room reservation screen. Next week I 
want to work more on the report and continue on improving the UX overall as there are still some thing that 
need improving.

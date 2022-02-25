# UserDataApplication

Before I started working on the project, I tried to understand and set up PrimeFaces framework (unsuccessfully). I still don't know, what was to problem, I tried many different setups, but I didn't get it to work.

After losing a day strugling with the PrimeFaces and JSF, I begun the project using Thymeleaf and Gradle in IntelliJ, because I had materials to learn these technologies.
At first, I planned the structure of the project. I set a goal, that I will make just one single template to handle all of the actions, which client is allowed to do in the app (edit, delete and filter users). The reason behind it is simply the fact, that I would enjoy using application which works this way. Also I wanted to make it a bit different than I am used to.

The first step I made was creating a fake Repository, providing the User data. I wanted to work with table containing more than 1000 Users, but I didn't want to create them all by myself. To speed up my work, I've made a function in the repository, which combines randomly few parameters and generates random Users for itself. The function is called in the constructor, so after starting the app, a List variable of the Repo is filled and everything is ready.

I really wanted to make possible for client, to filter Users by many parameters at once and keep the filtered users rendered after changing page or editing one of the users. That was the biggest challenge of the project.
To make it work, I used Http Session to store the filter parameters between any amount of requests. I am not sure if it's the best way to do it, but from the possible ways I've found, it still seems ideal to me.
I also changed the filtering principals a little, there is not an option to filter Users by Id, because I did not find it useful (might be just my point of view), but instead, Client may filter users by preset of the phone number.

For paging I made a Paging Service, which provides List of Lists filled with users and is able to change size of the pages by input parameter. I planned to add a button, which would change the page size on Clients request, but I was struggling with time, so I left it as it was.

To remain with the goal, I've set at the beginning, I didn't make a second template for editing users. Instead, I used thymeleaf to render a small form before the table, when an edit button is pressed. It has the parameters of selected user prefilled inside the inputs (or preselected, in case of select inputs) and it also uses some simple validations for the fields (in case someone would try to put letters in the phone number or something).

The application might not be completely restful (I used post methods for almost everything and all of the forms are sending data through query parameters), but I am satisfied with the result, because I managed to accomplish almost everything I wanted to.

There are some imperfections in styling, I tried super hard to have the User table centered in the page. I think I used every single centering method which exists, but the table just didn't want to be centered:) Also I am aware, that the layout (specifically spaces between inputs etc) is poorly optimazed for different sized monitors, but I have just basic knowledge of CSS and I had no time to spent on learning it.

I think that's everything important to mention here, I will keep the rest of explination for next time.

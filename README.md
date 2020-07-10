# Project 4 - Parstagram

**Parstagram** is a photo sharing app using Parse as its backend.

Time spent: 25 hours spent in total

<img src='https://raw.githubusercontent.com/Kingofkode/Parstagram/master/App%20icon.jpg' title='Video Walkthrough' width='200' alt='Video Walkthrough' />

## User Stories

The following **required** functionality is completed:

- [x] User sees app icon in home screen.
- [x] User can sign up to create a new account using Parse authentication
- [x] User can log in and log out of his or her account
- [x] The current signed in user is persisted across app restarts
- [x] User can take a photo, add a caption, and post it to "Instagram"
- [x] User can view the last 20 posts submitted to "Instagram"
- [x] User can pull to refresh the last 20 posts submitted to "Instagram"
- [x] User can view post details, including timestamp and caption.

The following **stretch** features are implemented:

- [x] Style the login page to look like the real Instagram login page.
- [x] Style the feed to look like the real Instagram feed.
- [x] User should switch between different tabs - viewing all posts (feed view), capture (camera and photo gallery view) and profile tabs (posts made) using fragments and a Bottom Navigation View.
- [ ] User can load more posts once he or she reaches the bottom of the feed using endless scrolling.
- [x] Show the username and creation time for each post
- [x] After the user submits a new post, show an indeterminate progress bar while the post is being uploaded to Parse
- User Profiles:
  - [ ] Allow the logged in user to add a profile photo
  - [ ] Display the profile photo with each post
  - [ ] Tapping on a post's username or profile photo goes to that user's profile page
  - [x] User Profile shows posts in a grid view
- [ ] User can comment on a post and see all comments for each post in the post details screen.
- [ ] User can like a post and see number of likes for each post in the post details screen.

The following **additional** features are implemented:

- [x] Applied the [View Binding library](https://guides.codepath.org/android/Reducing-View-Boilerplate-with-ViewBinding) to reduce view boilerplate
- [x] Added bio to users
- [x] Implemented Instagram style profile screen
- [x] Added image placeholder while photos are downloading
- [x] Image from camera view is properly rotated if app is run on a real device
- [x] Made compose screen a linear flow
- [x] Bottom navigation buttons are filled when they are selected
- [x] Sign up button is dynamically enabled/disabled depending on if the information inputted is valid

Please list two areas of the assignment you'd like to **discuss further with your peers** during the next class (examples include better ways to implement something, how to extend your app in certain ways, etc):

1. The best way to handle a social network on Parse (mutual followers etc)
2. How to persist data in fragments when they are reopened from the bottom navigation view

## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='https://github.com/Kingofkode/Parstagram/blob/master/Parstagram%20Demo.gif' title='Video Walkthrough' width='400' alt='Video Walkthrough' />


## Credits

List an 3rd party libraries, icons, graphics, or other assets you used in your app.

- [Android Async Http Client](http://loopj.com/android-async-http/) - networking library
- [Roboletric](https://github.com/robolectric/robolectric) - Android dependency management when writing unit tests

## Challenges encountered while building the app

- Setting up Parse required me to unrestrict an app using Facebook's Santa Rules

## License

    Copyright 2020 Isaiah Suarez

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

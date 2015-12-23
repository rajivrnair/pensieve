##Pensieve

###TLDR;
A simple app for developers to save their not-so-frequently-used commands/syntax/snippets of code - for the things we've done but have forgotten after a while.
It's like a personal wiki.

###The dramatic version
**Dumbledore:** _"I use the Pensieve. One simply siphons the excess thoughts from one's mind, pours them into the basin, and examines them at one's leisure. It becomes easier to spot patterns and links, you understand, when they are in this form."_

**Harry:** _"You mean... that stuff's your thoughts?"_

**Dumbledore:** _"Certainly."_

— Albus Dumbledore to Harry Potter[Goblet of Fire]

This is just a simple pensieve to help me remember stuff I've done before (mostly syntax of infrequently used commands).
The idea is to build something I need while setting up a _containerized_, _continuously-delivered_ app in the _cloud_ (because it looks good on my resume).

Requirements of the app:
- Save memories and retrieve them.
- Search for memories.
- Update/delete them.
- Share with others on your team (introduce users & teams).
- Monthly/fortnightly reminders (introduce mobile app - ionic?)

The plan is to do a dropwizard app that can be deployed onto DO or AWS.
The interface will be an Electron (http://electron.atom.io/) app for now.

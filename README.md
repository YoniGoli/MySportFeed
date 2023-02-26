# My Sport Feed

![The App](https://github.com/YoniGoli/MySportFeed/blob/main/assets/app.gif?raw=true)

[The app flow as a Gif](https://github.com/YoniGoli/MySportFeed/blob/main/assets/app.gif")


### Project Structure

![Project Structure](https://github.com/YoniGoli/MySportFeed/blob/main/assets/projectStructure.png?raw=true)

The Feed repository is the source of truth, this repository is responsible for fetching the data from the available data sources (Currently only local). Once, the data is fetched and sorted it’s transferred into the database.

The guideline is as follows: In order to display content, the UI components in the project will rely on the database only.

### Package By Feature
Each feature’s architecture has three layers: a data layer, a domain layer and a UI layer.

![Flow](https://github.com/YoniGoli/MySportFeed/blob/main/assets/architectureDiagarm.png?raw=true)


The architecture follows a reactive programming model with the unidirectional data flow. With the data layer at the bottom, the key concepts are:

- Higher layers react to changes in lower layers.
- Events flow down.
- Data flows up.


#### Example: Displaying matches on the home screen
When the app is first run it will attempt to load the list of matches resources from the relevant data source (Currently only local). Once loaded, these are shown to the user.

The following diagram shows the events which occur and how data flows from the relevant objects to achieve this.

![Flow](https://github.com/YoniGoli/MySportFeed/blob/main/assets/flowDiagarm.png?raw=true)


# ML-Fun

ML-Fun is my first take on machine learning algorithms. At it's core AI is trying to get to a goal (red dot) in as few steps as possible. Individual fitness function is being calculated for every dot basing on combination of distance from goal and minimal steps required to reach it.

![screenshot](https://github.com/gosu94/ML-Fun/blob/master/ml2.gif?raw=true)


## Useful references

**Generation** - when all dots are dead (ran into obstacles) new generation is being created with top genes from the previous one. In consequence each generation should have better (or same) result as previous one

**Alfa** - best individual from previous generation is being copied directly into new one and is displayed as a green dot.

**Min steps** - minimal steps individual has to take to get to the goal. This should be improving as generations passes. If generations are not getting to the goal yet, it is set to default 1000 value.

## Instructions
As a user you can create additional obstacles for AI to challenge. After creating each obstacle generation will be reset to 1.

To reset generations along with obstacles you can press enter key.


![screenshot](https://github.com/gosu94/ML-Fun/blob/master/ml3.gif?raw=true)


## Build and run

You can run this application straight away by running ML-Fun.jar from bin/ folder

To build this application by yourself you can use 

`chmod +x gradlew && ./gradlew desktop:dist on Linux and MacOS`

`gradlew desktop:dist on Windows`

The .jar file will be build into `desktop/build/libs/` folder


![screenshot](https://github.com/gosu94/ML-Fun/blob/master/ml1.gif?raw=true)

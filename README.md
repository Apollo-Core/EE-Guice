![Apollo-Core CI Java Repository](https://github.com/Apollo-Core/EE-Guice/workflows/Apollo-Core%20CI%20Java%20Repository/badge.svg) 
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/c4ee791652fe46c281a4611dfdd50676)](https://app.codacy.com/gh/Apollo-Core/EE-Guice?utm_source=github.com&utm_medium=referral&utm_content=Apollo-Core/EE-Guice&utm_campaign=Badge_Grade_Settings)
[![](https://jitpack.io/v/Apollo-Core/EE-Guice.svg)](https://jitpack.io/#Apollo-Core/EE-Guice)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

# EE-Guice
Repository implementing the functionality of the [Google Guice](https://github.com/google/guice) Modules used to enable configuring Apollo's orchestration via dynamic dependency injection.

## Relevance

### Repository relevant if

+ You want to get a high-level overview of the functionality of the Apollo platform (in this case you may also want to check out [EE-Core](https://github.com/Apollo-Core/EE-Core))
+ You want to implement new modules configuring Apollo

### Repository less relevant if

+ You want to use Apollo for application orchestration ([EE-Demo](https://github.com/Apollo-Core/EE-Demo) is probably a good place to start; the readme of [EE-IO](https://github.com/Apollo-Core/EE-IO) contains a description of the format of the input files required by Apollo)
+ You want implement a particular type of component, such as a scheduler (see [SC-Core](https://github.com/Apollo-Core/SC-Core)) or a new way of enacting functions

## Relations to other parts of Apollo-Core

### Depends On
  + EE-Core

### Used By
  + EE-IO
  + EE-Enactables
  + SC-Core
  + EE-Control
  + EE-Visualization
  + EE-Demo
  + EE-Deploy
  + EE-Docker


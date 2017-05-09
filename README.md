# brine

brine is a light-weight parser for Gherkin written in Java.

## Intentions

brine is intended to produce a object representation of a Gherkin `.feature` file consistent
with the implementation provided by [cucumber/gherkin](https://github.com/cucumber/gherkin), 
with the exception that brine allows tags to include spaces.
brine also enforces some additional rules, namely:
* Only one tag is allowed per line
* A Feature must contain at least one Scenario/Scenario Outline
* An examples table must contain at least one data row (i.e. only a header row is not allowed)

brine uses a slightly simplified grammar for Gherkin that produces a simplified object model. Additionally,
brine produces a single object (`Feature`) that captures all known information about a `.feature`, including
its location.

Unlike [cucumber/gherkin](https://github.com/cucumber/gherkin), brine produces no intermediate
abstract syntax tree (AST); rather, it generates a `Feature` directly. Also note that brine does
not associate tags hierarchically, i.e. a `Scenario` contains only the tags explicitly placed
on the represented Scenario, and does not include tags on the Feature.

## How to Use

The main entry point for the brine library is the `PickleJar`, which can produce a `Feature` 
from a `File`, `Reader`, or `URI`.

The various `cure` methods are fail-fast and will throw an exception if anything goes wrong. 
The `cureCollectErrors` methods accept a `List<String>` which will be populated with any errors 
encountered by the tokenizer or parser. The methods return the `Feature` representing the
Gherkin, ignoring any invalid lines.

### Exception Types

#### TokenizerException
*thrown when a line is encountered that the tokenizer cannot interpret, 
e.g. a examples/table line without a terminating `|`*

#### ParserException
*thrown when a line is encountered that was properly tokenized, 
but violates the Gherkin grammar, e.g. a description line between two steps*

### Example
Take the example `.feature` below:

```
@NormalTag
@Meta("this tag has spaces")
Feature: README Feature
 
  Scenario: README Scenario
  A simple explanation of this scenario.
  Just a little more information.
 
    Given some initial condition
    When a user performs an action
    Then there is some result
```

Invoking PickleJar.cure() on the above produces a `Feature` with the below json representation:

```
{
   "absoluteLocation":"C:/experiment/brine/src/main/resources/readme_example.feature",
   "relativeLocation":"src/main/resources/readme_example.feature",
   "line":3,
   "tags":[
      "@NormalTag",
      "@Meta(\"this tag has spaces\")"
   ],
   "name":"README Feature",
   "scenarios":[
      {
         "line":5,
         "name":"README Scenario",
         "description":[
            "A simple explanation of this scenario.",
            "Just a little more information."
         ],
         "steps":[
            {
               "line":9,
               "keyword":"Given",
               "step":"some initial condition"
            },
            {
               "line":10,
               "keyword":"When",
               "step":"a user performs an action"
            },
            {
               "line":11,
               "keyword":"Then",
               "step":"there is some result"
            }
         ]
      }
   ]
}
```

## Acknowledgements
brine is based heavily on the [cucumber/gherkin](https://github.com/cucumber/gherkin) implementation.
Like that implementation, brine employs [berp](https://github.com/gasparnagy/berp), an extensible parser
generator. 
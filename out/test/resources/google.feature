Feature: Testing

Scenario: Finding ravikiranj
  Given Open "https://www.google.com"
  When I search for "ravikiranj.net"
  Then Find by css selector ".g"
  Then Result href is "http://ravikiranj.net/"

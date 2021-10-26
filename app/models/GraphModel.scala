package models


case class D3jsGraph1(
                       graphTitle: String,
                       graphCredit: String,
                       graphId: String
                     )

case class D3jsParameters(
                         pageTitle: String,
                         graphData: D3jsGraph1
                         )
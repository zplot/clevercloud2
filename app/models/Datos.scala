package models

import play.twirl.api.Html


case class Section(
                    sectionId: Int,
                    sectionHeader: String,
                    sectionContentHTML: play.twirl.api.Html
                  )

case class Page(
                 pageTitle: String,
                 pageHeader: String,
                 sectionList: List[Section]
               )

case class TextData(
                     text: String,
                   )



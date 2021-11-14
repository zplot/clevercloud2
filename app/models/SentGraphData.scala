package models

/**
 * Presentation object used for displaying data in a template.
 *
 * Note that it's a good practice to keep the presentation DTO,
 * which are used for reads, distinct from the form processing DTO,
 * which are used for writes.
 */

/**
 * Esos datos son los que se pasan a la view showGraphAnswer que es la encargada de presentar el gráfico
 */


case class SentGraphData(
                          val name: String,
                          val function: String,
                          val xAxisFrom: String,
                          val xAxisTo: String,
                          val yAxisFrom: String,
                          val yAxisTo: String,
                          val grid: Boolean,
                          val graphColor: String
                        )



@file:OptIn(org.jetbrains.compose.resources.InternalResourceApi::class)

package wasmdemo.composeapp.generated.resources

import kotlin.OptIn
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi

@ExperimentalResourceApi
private object Drawable0 {
  public val book: DrawableResource = org.jetbrains.compose.resources.DrawableResource(
        "drawable:book",
          setOf(
            org.jetbrains.compose.resources.ResourceItem(setOf(), "drawable/book.jpeg"),
          )
      )

  public val compose_multiplatform: DrawableResource =
      org.jetbrains.compose.resources.DrawableResource(
        "drawable:compose_multiplatform",
          setOf(
            org.jetbrains.compose.resources.ResourceItem(setOf(),
          "drawable/compose-multiplatform.xml"),
          )
      )

  public val elisa: DrawableResource = org.jetbrains.compose.resources.DrawableResource(
        "drawable:elisa",
          setOf(
            org.jetbrains.compose.resources.ResourceItem(setOf(), "drawable/elisa.png"),
          )
      )

  public val headphones: DrawableResource = org.jetbrains.compose.resources.DrawableResource(
        "drawable:headphones",
          setOf(
            org.jetbrains.compose.resources.ResourceItem(setOf(), "drawable/headphones.png"),
          )
      )

  public val menu_book: DrawableResource = org.jetbrains.compose.resources.DrawableResource(
        "drawable:menu_book",
          setOf(
            org.jetbrains.compose.resources.ResourceItem(setOf(), "drawable/menu_book.png"),
          )
      )
}

@ExperimentalResourceApi
internal val Res.drawable.book: DrawableResource
  get() = Drawable0.book

@ExperimentalResourceApi
internal val Res.drawable.compose_multiplatform: DrawableResource
  get() = Drawable0.compose_multiplatform

@ExperimentalResourceApi
internal val Res.drawable.elisa: DrawableResource
  get() = Drawable0.elisa

@ExperimentalResourceApi
internal val Res.drawable.headphones: DrawableResource
  get() = Drawable0.headphones

@ExperimentalResourceApi
internal val Res.drawable.menu_book: DrawableResource
  get() = Drawable0.menu_book

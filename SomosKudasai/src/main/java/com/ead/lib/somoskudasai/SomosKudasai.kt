package com.ead.lib.somoskudasai

import com.ead.lib.somoskudasai.core.Properties
import com.ead.lib.somoskudasai.core.system.extensions.href
import com.ead.lib.somoskudasai.core.system.extensions.src
import com.ead.lib.somoskudasai.core.system.extensions.suspend
import com.ead.lib.somoskudasai.models.Home
import com.ead.lib.somoskudasai.models.News
import com.ead.lib.somoskudasai.models.NewsPreview
import com.ead.lib.somoskudasai.models.Tag
import com.ead.lib.somoskudasai.models.html_tags.H1
import com.ead.lib.somoskudasai.models.html_tags.H2
import com.ead.lib.somoskudasai.models.html_tags.H3
import com.ead.lib.somoskudasai.models.html_tags.H4
import com.ead.lib.somoskudasai.models.html_tags.H5
import com.ead.lib.somoskudasai.models.html_tags.Iframe
import com.ead.lib.somoskudasai.models.html_tags.Image
import com.ead.lib.somoskudasai.models.html_tags.P
import com.ead.lib.somoskudasai.models.html_tags.Ul
import com.ead.lib.somoskudasai.models.html_tags.Video
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

private class Network {

    private val collectorStructure = mutableListOf<Tag>()

    suspend fun getHome() : Home? {

        val somosKudasaiPage = Jsoup.connect(Properties.MAIN_URL).get().suspend() ?: return null

        return Home(
            trendsOfTheWeek = getTrendsOfTheWeek(somosKudasaiPage),
            recentNews = getRecentNews(somosKudasaiPage),
            japanNews = getJapanNews(somosKudasaiPage),
            otakuCulture = getOtakuCulture(somosKudasaiPage),
            reviews = getReviews(somosKudasaiPage)
        )
    }

    private fun getTrendsOfTheWeek(page : Document) : List<NewsPreview>  {

        val classListItems = page
            .select("article.ar.por.lg")

        return classListItems.map { element ->
            NewsPreview(
                title = element.select("header.ar-hd.poa.z2.pd1.xl-pd3 h2.ar-title.white-co.mab.fz5.xl-fz7").text(),
                image = element.select("figure.im.brd1.black-bg.z1 img.attachment-post-thumbnail.size-post-thumbnail.wp-post-image").src(),
                type = element.select("header.ar-hd.poa.z2.pd1.xl-pd3 span.typ.ttu.fwb.fz2.white-bg.primary-co.brd1.dib.pdx1.mab").text(),
                date = element.select("header.ar-hd.poa.z2.pd1 div.ar-mt.white-co.fz3 span.db.op5").text(),
                author = element.select("header.ar-hd.poa.z2.pd1 span.dn.fwb.op7").text(),
                url = element.select("a.lnk-blk").href()
            )
        }
    }

    private fun getRecentNews(page : Document) : List<NewsPreview>  {

        val classListItems = page
            .select("div.news-list.dg.gg1.gt1.xs-gt2.md-gt3.xl-gt4.xl-gg2 article.ar.por")

        return classListItems.map { element ->
            NewsPreview(
                title = element.select("header.ar-hd.poa.z2.pd1 h2.ar-title.white-co.mab.fz4.lg-fz5").text(),
                image = element.select("figure.im.brd1.black-bg.z1 img.attachment-post-thumbnail.size-post-thumbnail.wp-post-image").src(),
                type = element.select("header.ar-hd.poa.z2.pd1 span.typ.ttu.fwb.fz2.white-bg.primary-co.brd1.dib.pdx1.mab").text(),
                date = element.select("header.ar-hd.poa.z2.pd1 div.ar-mt.white-co.fz3").text(),
                author = null,
                url = element.select("a.lnk-blk").href()
            )
        }
    }


    private fun getJapanNews(page : Document) : List<NewsPreview>  {

        val classListItems = page
            .select("section.bx.pdy2.lg-pdy4.por.scn div.dg.gt1.md-gt2.gg1.xl-gg3 article.ar.lg.por")

        return classListItems.map { element ->
            NewsPreview(
                title = element.select("header.ar-hd.poa.z2.pd1 h2.ar-title.white-co.mab.fz4.xs-fz7").text(),
                image = element.select("figure.im.brd1.black-bg.z1 img.attachment-post-thumbnail.size-post-thumbnail.wp-post-image").src(),
                type = null,
                date = element.select("header.ar-hd.poa.z2.pd1 div.ar-mt.white-co.fz3").text(),
                author = null,
                url = element.select("a.lnk-blk").href()
            )
        }
    }

    private fun getOtakuCulture(page : Document) : List<NewsPreview>  {

        val section = page.select("section.bx.pdy2.lg-pdy4.por.drk").getOrNull(1) ?: return emptyList()
        val classListItems = section.select("div.dg.gt1.md-gt2.gg1.xl-gg3 article.ar.lg.por")

        return classListItems.map { element ->
            NewsPreview(
                title = element.select("header.ar-hd.poa.z2.pd1 h2.ar-title.white-co.mab.fz4.xs-fz7").text(),
                image = element.select("figure.im.brd1.black-bg.z1 img.attachment-post-thumbnail.size-post-thumbnail.wp-post-image").src(),
                type = null,
                date = element.select("header.ar-hd.poa.z2.pd1 div.ar-mt.white-co.fz3").text(),
                author = null,
                url = element.select("a.lnk-blk").href()
            )
        }
    }

    private fun getReviews(page : Document) : List<NewsPreview>  {

        val classListItems = page
            .select("div.ar-reviews div.swiper-container div.swiper-wrapper div.swiper-slide article.ar.por")

        return classListItems.map { element ->
            NewsPreview(
                title = element.select("header.ar-hd.poa.z2.pd1 h2.ar-title.white-co.mab.fz4.lg-fz5").text(),
                image = element.select("figure.im.brd1.black-bg.z1 img.attachment-post-thumbnail.size-post-thumbnail.wp-post-image").src(),
                type = null,
                date = element.select("header.ar-hd.poa.z2.pd1 div.ar-mt.white-co.fz3 span.db.op5").text(),
                author = element.select("header.ar-hd.poa.z2.pd1 div.ar-mt.white-co.fz3 span.dn.fwb.op7").text(),
                url = element.select("a.lnk-blk").href()
            )
        }
    }

    suspend fun getNewsInfo(url : String) : News? {

        val newsPage = Jsoup.connect(url).get().suspend() ?: return null

        val headerSection = newsPage.select("section.single.por.z1.pdb4")
        val footerSection = newsPage.select("section.bx.bx-thr.dfx.fxw.pdt3.mab3.pdb1 div.dfx.aic.mab1.sm-fg1.sm-mar1")

        return News(
            title = headerSection.select("article.ar.lg.sngl.pdt4 header.ar-hd.por.z2.pdl3 h1.ar-title.white-co.mab1.pdt.fz5.lg-fz7.xl-fz8.mar").text(),
            image = headerSection.select("article.ar.lg.sngl.pdt4 figure.im.black-bg.z-1 img.attachment-post-thumbnail.size-post-thumbnail.wp-post-image").src(),
            type = headerSection.select("article.ar.lg.sngl.pdt4 header.ar-hd.por.z2.pdl3 p.mab0 span.typ.ttu.fwb.fz2.white-bg.primary-co.brd1.dib.pdx1.mab.mar").text(),
            date = headerSection.select("article.ar.lg.sngl.pdt4 header.ar-hd.por.z2.pdl3 div.ar-mt.white-co span.op5.mar2").text(),
            author = headerSection.select("article.ar.lg.sngl.pdt4 header.ar-hd.por.z2.pdl3 div.ar-mt.white-co span.fwb.op7.mar2").text(),
            authorImage = footerSection.select("figure.avatar.mar2.ast img.brdc").src(),
            authorWords = footerSection.select("div.fg1 p.mab0").text(),
            structure =  collectorStructure.apply {

                clear()

                newsPage.select("section.bx.pdy2 div.entry.xl-fz5").first()?.children()?.forEach { element ->
                    when (element.tagName()) {
                        "h1" -> add(H1(element.text()))
                        "h2" -> add(H2(element.text()))
                        "h3" -> add(H3(element.text()))
                        "h4" -> add(H4(element.text()))
                        "h5" -> add(H5(element.text()))
                        "p" -> add(P(element.text()))
                        "div" -> {
                            if (element.className() == "wp-block-image" || element.className() == "im black-bg z-1") {
                                val firstChild = element.firstElementChild()
                                firstChild?.apply {
                                    if (tagName() == "figure") {
                                        val secondChild = firstElementChild()
                                        secondChild?.let {
                                            add(Image(it.attr("src")))
                                        }
                                    } else {
                                        add(Image(firstChild.attr("src")))
                                    }
                                }
                            }
                        }
                        "figure" -> {
                            for (item in element.children()) {
                                if (item.tagName() == "figure") {
                                    add(Image(item.child(0).attr("src")))
                                } else {
                                    if (item.tagName() == "img") {
                                        add(Image(item.attr("src")))
                                    }
                                }
                            }
                            val child = element.children().lastOrNull()
                            child?.apply {
                                if (tagName() == "figcaption") {
                                    val insideChild = firstElementChild()
                                    insideChild?.let {
                                        if (it.tagName() == "mark") {
                                            add(H2(it.text()))
                                        }
                                    }
                                }
                            }
                        }
                        "center" -> {
                            val child = element.firstElementChild()
                            child?.apply {
                                if (tagName() == "video") {
                                    add(Video(attr("src")))
                                }
                            }
                        }
                        "ul" -> add(Ul(element.children().map { " · " + it.text() }))
                        "iframe" -> add(Iframe(element.attr("src")))
                        "video" -> add(Video(element.attr("src")))
                    }
                }
            }
        )
    }
}

object SomosKudasai {

    private val network : Network = Network()

    suspend fun getHome() = network.getHome()

    suspend fun getNewsInfo(url : String) = network.getNewsInfo(url)
}
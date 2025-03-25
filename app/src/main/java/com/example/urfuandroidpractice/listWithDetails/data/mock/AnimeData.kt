package com.example.urfuandroidpractice.listWithDetails.data.mock

import com.example.urfuandroidpractice.listWithDetails.domain.entity.AnimeFullEntity
import com.example.urfuandroidpractice.listWithDetails.domain.entity.AnimeKind
import com.example.urfuandroidpractice.listWithDetails.domain.entity.AnimeShortEntity
import com.example.urfuandroidpractice.listWithDetails.domain.entity.AnimeStatus
import com.example.urfuandroidpractice.listWithDetails.domain.entity.ImageInfo

object AnimeData {
    val animeShort = listOf(
        AnimeShortEntity(
            id = 16498,
            name = "Shingeki no Kyojin",
            russian = "Атака Титанов",
            image = ImageInfo(
                original = "/system/animes/original/16498.jpg?1711973439",
                preview = "/system/animes/preview/16498.jpg?1711973439",
                x96 = "/system/animes/x96/16498.jpg?1711973439",
                x48 = "/system/animes/x48/16498.jpg?1711973439"
            ),
            status = AnimeStatus.RELEASED,
            kind = AnimeKind.TV,
            score = 8.55f,
            episodes = 25,
            episodesAired = 25,
            airedOn = "2013-04-07",
            releasedOn = "2013-09-29",
        ),
        AnimeShortEntity(
            id = 1535,
            name = "Death Note",
            russian = "Тетрадь смерти",
            image = ImageInfo(
                original = "/system/animes/original/1535.jpg",
                preview = "/system/animes/preview/1535.jpg",
                x96 = "/system/animes/x96/1535.jpg",
                x48 = "/system/animes/x48/1535.jpg"
            ),
            kind = AnimeKind.TV,
            score = 8.62f,
            status = AnimeStatus.RELEASED,
            episodes = 37,
            episodesAired = 37,
            airedOn = "2006-10-04",
            releasedOn = "2007-06-27"
        ),
        AnimeShortEntity(
            id = 5114,
            name = "Fullmetal Alchemist: Brotherhood",
            russian = "Стальной алхимик: Братство",
            image = ImageInfo(
                original = "/system/animes/original/5114.jpg?1711949773",
                preview = "/system/animes/preview/5114.jpg?1711949773",
                x96 = "/system/animes/x96/5114.jpg?1711949773",
                x48 = "/system/animes/x48/5114.jpg?1711949773"
            ),
            kind = AnimeKind.TV,
            score = 9.1f,
            status = AnimeStatus.RELEASED,
            episodes = 64,
            episodesAired = 0,
            airedOn = "2009-04-05",
            releasedOn = "2010-07-04"
        ),
        AnimeShortEntity(
            id = 30276,
            name = "One Punch Man",
            russian = "One Punch Man",
            image = ImageInfo(
                original = "/system/animes/original/30276.jpg?1711967568",
                preview = "/system/animes/preview/30276.jpg?1711967568",
                x96 = "/system/animes/x96/30276.jpg?1711967568",
                x48 = "/system/animes/x48/30276.jpg?1711967568"
            ),
            kind = AnimeKind.TV,
            score = 8.49f,
            status = AnimeStatus.RELEASED,
            episodes = 12,
            episodesAired = 12,
            airedOn = "2015-10-05",
            releasedOn = "2015-12-21"
        )
    )

    val animeFull = listOf(
        AnimeFullEntity(
            id = 16498,
            name = "Shingeki no Kyojin",
            russian = "Атака Титанов",
            image = ImageInfo(
                original = "/system/animes/original/16498.jpg?1711973439",
                preview = "/system/animes/preview/16498.jpg?1711973439",
                x96 = "/system/animes/x96/16498.jpg?1711973439",
                x48 = "/system/animes/x48/16498.jpg?1711973439"
            ),
            kind = AnimeKind.TV,
            score = 8.55f,
            status = AnimeStatus.RELEASED,
            episodes = 25,
            episodesAired = 25,
            airedOn = "2013-04-07",
            releasedOn = "2013-09-29",
            rating = "r",
            duration = 24,
            description = "С давних времён человечество ведёт свою борьбу с титанами. Титаны — это огромные существа, ростом с многоэтажный дом, которые не обладают большим интеллектом, но сила их просто ужасна. Они едят людей и получают от этого удовольствие. После продолжительной борьбы остатки человечества создали стену, окружившую мир людей, через которую не пройдут даже титаны.\nС тех пор прошло сто лет. Человечество мирно живёт под защитой стены. Но в один день мальчик Эрен [エレン・イェーガー] и его сводная сестра Микаса [ミカサ・アッカーマン] становятся свидетелями страшного события: участок стены был разрушен супертитаном, появившимся прямо из воздуха. Титаны атакуют город, и двое детей в ужасе видят, как один из монстров заживо съедает их мать.\nБрат и сестра выживают, и Эрен клянётся, что убьёт всех титанов и отомстит за всё человечество!",
            genres = listOf("Shounen", "Action", "Drama", "Thriller", "Military")
        ),
        AnimeFullEntity(
            id = 1535,
            name = "Death Note",
            russian = "Тетрадь смерти",
            image = ImageInfo(
                original = "/system/animes/original/1535.jpg?1711947446",
                preview = "/system/animes/preview/1535.jpg?1711947446",
                x96 = "/system/animes/x96/1535.jpg?1711947446",
                x48 = "/system/animes/x48/1535.jpg?1711947446"
            ),
            kind = AnimeKind.TV,
            score = 8.62f,
            status = AnimeStatus.RELEASED,
            episodes = 37,
            episodesAired = 0,
            airedOn = "2006-10-04",
            releasedOn = "2007-06-27",
            rating = "r",
            duration = 23,
            description = "Изнывающий от скуки Синигами Рюк [リューク] бросает одну из своих Тетрадей смерти в мир людей. Просто так, потехи ради, посмотреть, что из этого выйдет.\nМежду тем, в Японии на школьной лужайке эту самую тетрадь находит Лайт Ягами [夜神月] — лучший ученик школы, сын полицейского. Заинтригованный инструкцией на обложке, он забирает тетрадь домой и пробует её в деле, вписав туда имя преступника. А вдруг сработает?\nВскоре весь мир замечает странные массовые смерти преступников, а в сети загадочного убийцу окрещают Кирой.\nДля поимки Киры [夜神月] Интерпол привлекает легендарного детектива L, в одиночку раскрывавшего наиболее сложные и запутанные преступления. Кто такой L на самом деле — не знает никто.\nОтныне в противостоянии Киры и L предстоит победить тому, кто первым раскроет истинную личность противника и раньше оппонента нанесёт удар.",
            genres = listOf("Supernatural", "Thriller", "Psychological", "Shounen")
        ),
        AnimeFullEntity(
            id = 5114,
            name = "Fullmetal Alchemist: Brotherhood",
            russian = "Стальной алхимик: Братство",
            image = ImageInfo(
                original = "/system/animes/original/5114.jpg?1711949773",
                preview = "/system/animes/preview/5114.jpg?1711949773",
                x96 = "/system/animes/x96/5114.jpg?1711949773",
                x48 = "/system/animes/x48/5114.jpg?1711949773"
            ),
            kind = AnimeKind.TV,
            score = 9.1f,
            status = AnimeStatus.RELEASED,
            episodes = 64,
            episodesAired = 0,
            airedOn = "2009-04-05",
            releasedOn = "2010-07-04",
            rating = "r",
            duration = 24,
            description = "Ремейк одноимённого аниме-сериала «Стальной алхимик» 2003 года, более строго следующий событиям, описанным в манге.\n\nВ этом мире существуют алхимики — люди, владеющие искусством алхимии, способностью манипулировать материей и преобразовывать вещество. Все они ограничены основным Законом алхимии: нельзя алхимическим путём получить что-то, не пожертвовав чем-то равноценным полученному. Лишь с помощью легендарного философского камня, способ создания которого утерян, можно обойти этот Закон.\nГлавные герои, братья Эдвард [エドワード・エルリック] и Альфонс [アルフォンス・エルリック] Элрики, пострадали в детстве при попытке вернуть к жизни свою мать, умершую от болезни. Они забыли основной Закон алхимии и жестоко поплатились за это: Альфонс потерял всё своё тело, а Эдвард — руку и ногу. Эдвард сумел спасти лишь душу Альфонса, запечатав её в старинных доспехах.\nСпустя много лет Эдвард сдаёт государственный экзамен на звание алхимика и получает прозвище «Стальной Алхимик». Братья начинают путешествие с целью найти философский камень и вернуть с его помощью утраченное много лет назад.",
            genres = listOf("Shounen", "Action", "Adventure", "Drama", "Fantasy", "Military")
        ), AnimeFullEntity(
            id = 30276,
            name = "One Punch Man",
            russian = "One Punch Man",
            image = ImageInfo(
                original = "/system/animes/original/30276.jpg?1711967568",
                preview = "/system/animes/preview/30276.jpg?1711967568",
                x96 = "/system/animes/x96/30276.jpg?1711967568",
                x48 = "/system/animes/x48/30276.jpg?1711967568"
            ),
            kind = AnimeKind.TV,
            score = 8.49f,
            status = AnimeStatus.RELEASED,
            episodes = 12,
            episodesAired = 12,
            airedOn = "2015-10-05",
            releasedOn = "2015-12-21",
            rating = "r",
            duration = 24,
            description = "Вы устали от запоминания суператак и бесконечных доспехов, но душа просит адреналина и драк? Тогда эта новая экшен-комедия от студии Madhouse для вас!\nГлавный герой не размахивает мечом, не выкрикивает боевой клич и вообще не отличается ничем, особенно героическим. Всё в этом молодом человеке по имени Сайтама так и вопит: «заурядный» — и его лысая голова, и его хилое телосложение. Однако у этого среднестатистического по всем параметрам парня совсем не среднестатистические проблемы... Потому что на самом деле он — супергерой, жаждущий битвы с суперкрутыми противниками. Загвоздка в том, что, отыскав наконец перспективного кандидата на роль главного врага, Сайтама выносит его с одного удара. Сможет ли Сайтама заиметь себе могучего злодея, который смог бы достойно противостоять ему? Следите за Ванпанчменом в его уморительных приключениях, пока среди многочисленных плохих парней он, несмотря ни на что, будет пытаться найти «своего»!",
            genres = listOf("Seinen", "Action", "Comedy", "Parody", "Super Power")
        )
    )
}
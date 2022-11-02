-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1:3306
-- Время создания: Ноя 02 2022 г., 12:19
-- Версия сервера: 5.7.39
-- Версия PHP: 7.2.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `final_bd`
--

-- --------------------------------------------------------

--
-- Структура таблицы `car`
--

CREATE TABLE `car` (
  `idcar` bigint(20) NOT NULL,
  `carcost` int(11) NOT NULL,
  `carenginehp` int(11) NOT NULL,
  `carengine_size` decimal(4,2) NOT NULL,
  `carname` varchar(255) DEFAULT NULL,
  `idcolor_idcolor` bigint(20) DEFAULT NULL,
  `iddrive_iddrive` bigint(20) DEFAULT NULL,
  `idfuel_idfuel` bigint(20) DEFAULT NULL,
  `idtransmission_idtransmission` bigint(20) DEFAULT NULL,
  `idtype_idtype` bigint(20) DEFAULT NULL,
  `idcar_photo_idphoto` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `car`
--

INSERT INTO `car` (`idcar`, `carcost`, `carenginehp`, `carengine_size`, `carname`, `idcolor_idcolor`, `iddrive_iddrive`, `idfuel_idfuel`, `idtransmission_idtransmission`, `idtype_idtype`, `idcar_photo_idphoto`) VALUES
(2, 1155000, 204, '2.50', 'BMW 5 серии 523i VI (F10/F11/F07)\r\n', 1, 2, 1, 1, 1, 2),
(12, 1231231, 220, '3.00', 'Mitsubishi 3000GT', 1, 2, 1, 1, 1, 1),
(13, 599990000, 7000, '5.50', 'Дредноут S-класса', 2, 3, 4, 4, 3, 4);

-- --------------------------------------------------------

--
-- Структура таблицы `car_photo`
--

CREATE TABLE `car_photo` (
  `idphoto` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `photolink` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `car_photo`
--

INSERT INTO `car_photo` (`idphoto`, `description`, `photolink`) VALUES
(1, 'Mitsubishi 3000GT', 'https://avatars.mds.yandex.net/get-autoru-vos/2153865/6995ef0dba224e816a590f313d296335/1200x900n'),
(2, 'BMW 5 серии 523i VI (F10/F11/F07)', 'https://avatars.mds.yandex.net/get-autoru-vos/2157912/f20d415b171685110c147729854bd727/1200x900n'),
(3, 'Mazda RX-7 III (FD)', 'https://avatars.mds.yandex.net/get-autoru-vos/5264226/3ff09a8a13c64f418eed45eff1819b05/1200x900n'),
(4, 'Дредноут С-класса с кожаным диваном', 'http://www.gamer.ru/system/attached_images/images/000/302/438/original/40k_011.jpg');

-- --------------------------------------------------------

--
-- Структура таблицы `car_saloon`
--

CREATE TABLE `car_saloon` (
  `saloon_id` bigint(20) NOT NULL,
  `car_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `car_saloon`
--

INSERT INTO `car_saloon` (`saloon_id`, `car_id`) VALUES
(2, 2),
(3, 13),
(3, 13),
(2, 13);

-- --------------------------------------------------------

--
-- Структура таблицы `car_type`
--

CREATE TABLE `car_type` (
  `idtype` bigint(20) NOT NULL,
  `typename` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `car_type`
--

INSERT INTO `car_type` (`idtype`, `typename`) VALUES
(1, 'Внедорожник'),
(2, 'Купе'),
(3, 'Шагоход'),
(4, 'Глянцевый'),
(5, 'Матовый');

-- --------------------------------------------------------

--
-- Структура таблицы `color`
--

CREATE TABLE `color` (
  `idcolor` bigint(20) NOT NULL,
  `colormain` varchar(255) DEFAULT NULL,
  `colorsub` varchar(255) DEFAULT NULL,
  `color_type_idcolor_type` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `color`
--

INSERT INTO `color` (`idcolor`, `colormain`, `colorsub`, `color_type_idcolor_type`) VALUES
(1, 'Чёрный', 'Чёрный', 1),
(2, 'Жёлтый', 'Белый', 3),
(5, 'Синий', 'Отсутствует', 3),
(7, 'Оранжевый', 'Черный', 3);

-- --------------------------------------------------------

--
-- Структура таблицы `color_types`
--

CREATE TABLE `color_types` (
  `idcolor_type` bigint(20) NOT NULL,
  `color_type` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `color_types`
--

INSERT INTO `color_types` (`idcolor_type`, `color_type`) VALUES
(1, 'Металлик'),
(3, 'Обычный');

-- --------------------------------------------------------

--
-- Структура таблицы `drive_type`
--

CREATE TABLE `drive_type` (
  `iddrive` bigint(20) NOT NULL,
  `drivename` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `drive_type`
--

INSERT INTO `drive_type` (`iddrive`, `drivename`) VALUES
(2, 'Задний'),
(3, 'Полноприводный');

-- --------------------------------------------------------

--
-- Структура таблицы `favorite`
--

CREATE TABLE `favorite` (
  `user_id` bigint(20) NOT NULL,
  `car_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `favorite`
--

INSERT INTO `favorite` (`user_id`, `car_id`) VALUES
(2, 13),
(6, 2);

-- --------------------------------------------------------

--
-- Структура таблицы `fuel_type`
--

CREATE TABLE `fuel_type` (
  `idfuel` bigint(20) NOT NULL,
  `fuelname` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `fuel_type`
--

INSERT INTO `fuel_type` (`idfuel`, `fuelname`) VALUES
(1, 'Бензин'),
(2, 'Дизель'),
(3, '92'),
(4, '95');

-- --------------------------------------------------------

--
-- Структура таблицы `gender`
--

CREATE TABLE `gender` (
  `idgender` bigint(20) NOT NULL,
  `gendername` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `gender`
--

INSERT INTO `gender` (`idgender`, `gendername`) VALUES
(1, 'Мужской пол'),
(2, 'Женский пол');

-- --------------------------------------------------------

--
-- Структура таблицы `saloon`
--

CREATE TABLE `saloon` (
  `idsaloon` bigint(20) NOT NULL,
  `saloonaddress` varchar(255) DEFAULT NULL,
  `saloonname` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `saloon`
--

INSERT INTO `saloon` (`idsaloon`, `saloonaddress`, `saloonname`) VALUES
(2, 'ул. Зорге, 17', 'Автодом BMW Зорге Официальный дилер'),
(3, 'Ул. Некрасова', 'Фабрика');

-- --------------------------------------------------------

--
-- Структура таблицы `transmission`
--

CREATE TABLE `transmission` (
  `idtransmission` bigint(20) NOT NULL,
  `transmissionname` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `transmission`
--

INSERT INTO `transmission` (`idtransmission`, `transmissionname`) VALUES
(1, 'Автомат'),
(3, 'Механика'),
(4, 'Полуавтомат');

-- --------------------------------------------------------

--
-- Структура таблицы `user`
--

CREATE TABLE `user` (
  `iduser` bigint(20) NOT NULL,
  `active` bit(1) DEFAULT NULL,
  `login` varchar(20) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `profile_pic` varchar(255) DEFAULT NULL,
  `user_data_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `user`
--

INSERT INTO `user` (`iduser`, `active`, `login`, `password`, `profile_pic`, `user_data_id`) VALUES
(1, b'1', 'thvele', '$2a$08$ouw4bSxIP.TjudxwYzN0Ced4pNHFZBJhjK3DrdSNasfnxBl59La8S', 'https://i.pinimg.com/564x/f8/23/6f/f8236fc44b05e5d24adffe880261ea77.jpg', 1),
(2, b'0', '1231', '$2a$08$10izCU0YKkzdUy89k2AMBee7P0XdZsieufx98sDVuKTFy90AR8TiO', 'https://i.pinimg.com/originals/c3/48/37/c348377c408262c6ce518381ca6fd099.gif', 2),
(6, b'1', 'testUser', '$2a$08$EdmukDiYRwSbxeh6hQyKTenTzLK2KoxdyxmdUMT1sYuw8iiducLba', 'https://i.pinimg.com/originals/c3/48/37/c348377c408262c6ce518381ca6fd099.gif', 6);

-- --------------------------------------------------------

--
-- Структура таблицы `user_data`
--

CREATE TABLE `user_data` (
  `iddata` bigint(20) NOT NULL,
  `fio` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `idgender_idgender` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `user_data`
--

INSERT INTO `user_data` (`iddata`, `fio`, `email`, `phone`, `idgender_idgender`) VALUES
(1, 'Слободянюк Игорь Сергеевич', 'isip_i.s.slobodyanyuk@mpt.ru', '+79164700906', 2),
(2, 'Некрутенков Виниамин Сергеевич', 'thvele@list.ru', '+79164700906', 2),
(6, 'Иванов Иван Иванович', 'example@ex.com', '+78005553535', 1);

-- --------------------------------------------------------

--
-- Структура таблицы `user_role`
--

CREATE TABLE `user_role` (
  `user_id` bigint(20) NOT NULL,
  `roles` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `user_role`
--

INSERT INTO `user_role` (`user_id`, `roles`) VALUES
(1, 'ADMIN'),
(6, 'USER'),
(2, 'REDACTOR'),
(2, 'USER'),
(2, 'ADMIN');

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `car`
--
ALTER TABLE `car`
  ADD PRIMARY KEY (`idcar`),
  ADD KEY `FKier0f3imqw4luu6batj4mrbfn` (`idcolor_idcolor`),
  ADD KEY `FKdofewl557okpb0xgy6x3fd1nt` (`iddrive_iddrive`),
  ADD KEY `FKfosbf6p9fyn8lbauif271np4f` (`idfuel_idfuel`),
  ADD KEY `FKej7cu6ud8j291tf7oyuii560i` (`idtransmission_idtransmission`),
  ADD KEY `FK1ap7ftoq2tmwkcbwqg409fe5a` (`idtype_idtype`),
  ADD KEY `FKh4rjxamdcwriv6ife8mh8dew2` (`idcar_photo_idphoto`);

--
-- Индексы таблицы `car_photo`
--
ALTER TABLE `car_photo`
  ADD PRIMARY KEY (`idphoto`);

--
-- Индексы таблицы `car_saloon`
--
ALTER TABLE `car_saloon`
  ADD KEY `FKss0gujk8ip1d7rj04bw17wxkp` (`car_id`),
  ADD KEY `FKbh3540br5hkxqjsi93qsxroit` (`saloon_id`);

--
-- Индексы таблицы `car_type`
--
ALTER TABLE `car_type`
  ADD PRIMARY KEY (`idtype`);

--
-- Индексы таблицы `color`
--
ALTER TABLE `color`
  ADD PRIMARY KEY (`idcolor`),
  ADD KEY `FKbq2d9c4g1lj421w8w97pdr0o6` (`color_type_idcolor_type`);

--
-- Индексы таблицы `color_types`
--
ALTER TABLE `color_types`
  ADD PRIMARY KEY (`idcolor_type`);

--
-- Индексы таблицы `drive_type`
--
ALTER TABLE `drive_type`
  ADD PRIMARY KEY (`iddrive`);

--
-- Индексы таблицы `favorite`
--
ALTER TABLE `favorite`
  ADD KEY `FKgng78lym9ghokl8n4ge710mcu` (`car_id`),
  ADD KEY `FKh3f2dg11ibnht4fvnmx60jcif` (`user_id`);

--
-- Индексы таблицы `fuel_type`
--
ALTER TABLE `fuel_type`
  ADD PRIMARY KEY (`idfuel`);

--
-- Индексы таблицы `gender`
--
ALTER TABLE `gender`
  ADD PRIMARY KEY (`idgender`);

--
-- Индексы таблицы `saloon`
--
ALTER TABLE `saloon`
  ADD PRIMARY KEY (`idsaloon`);

--
-- Индексы таблицы `transmission`
--
ALTER TABLE `transmission`
  ADD PRIMARY KEY (`idtransmission`);

--
-- Индексы таблицы `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`iduser`),
  ADD KEY `FKkjrwwk4ag3bq7rvirdd2mk2eq` (`user_data_id`);

--
-- Индексы таблицы `user_data`
--
ALTER TABLE `user_data`
  ADD PRIMARY KEY (`iddata`),
  ADD KEY `FK8swoat8jrotg147oumt2jx33y` (`idgender_idgender`);

--
-- Индексы таблицы `user_role`
--
ALTER TABLE `user_role`
  ADD KEY `FK859n2jvi8ivhui0rl0esws6o` (`user_id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `car`
--
ALTER TABLE `car`
  MODIFY `idcar` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT для таблицы `car_photo`
--
ALTER TABLE `car_photo`
  MODIFY `idphoto` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT для таблицы `car_type`
--
ALTER TABLE `car_type`
  MODIFY `idtype` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT для таблицы `color`
--
ALTER TABLE `color`
  MODIFY `idcolor` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT для таблицы `color_types`
--
ALTER TABLE `color_types`
  MODIFY `idcolor_type` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT для таблицы `drive_type`
--
ALTER TABLE `drive_type`
  MODIFY `iddrive` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT для таблицы `fuel_type`
--
ALTER TABLE `fuel_type`
  MODIFY `idfuel` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT для таблицы `gender`
--
ALTER TABLE `gender`
  MODIFY `idgender` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT для таблицы `saloon`
--
ALTER TABLE `saloon`
  MODIFY `idsaloon` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT для таблицы `transmission`
--
ALTER TABLE `transmission`
  MODIFY `idtransmission` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT для таблицы `user`
--
ALTER TABLE `user`
  MODIFY `iduser` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT для таблицы `user_data`
--
ALTER TABLE `user_data`
  MODIFY `iddata` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `car`
--
ALTER TABLE `car`
  ADD CONSTRAINT `FK1ap7ftoq2tmwkcbwqg409fe5a` FOREIGN KEY (`idtype_idtype`) REFERENCES `car_type` (`idtype`),
  ADD CONSTRAINT `FKdofewl557okpb0xgy6x3fd1nt` FOREIGN KEY (`iddrive_iddrive`) REFERENCES `drive_type` (`iddrive`),
  ADD CONSTRAINT `FKej7cu6ud8j291tf7oyuii560i` FOREIGN KEY (`idtransmission_idtransmission`) REFERENCES `transmission` (`idtransmission`),
  ADD CONSTRAINT `FKfosbf6p9fyn8lbauif271np4f` FOREIGN KEY (`idfuel_idfuel`) REFERENCES `fuel_type` (`idfuel`),
  ADD CONSTRAINT `FKh4rjxamdcwriv6ife8mh8dew2` FOREIGN KEY (`idcar_photo_idphoto`) REFERENCES `car_photo` (`idphoto`),
  ADD CONSTRAINT `FKier0f3imqw4luu6batj4mrbfn` FOREIGN KEY (`idcolor_idcolor`) REFERENCES `color` (`idcolor`);

--
-- Ограничения внешнего ключа таблицы `car_saloon`
--
ALTER TABLE `car_saloon`
  ADD CONSTRAINT `FKbh3540br5hkxqjsi93qsxroit` FOREIGN KEY (`saloon_id`) REFERENCES `saloon` (`idsaloon`),
  ADD CONSTRAINT `FKss0gujk8ip1d7rj04bw17wxkp` FOREIGN KEY (`car_id`) REFERENCES `car` (`idcar`);

--
-- Ограничения внешнего ключа таблицы `color`
--
ALTER TABLE `color`
  ADD CONSTRAINT `FKbq2d9c4g1lj421w8w97pdr0o6` FOREIGN KEY (`color_type_idcolor_type`) REFERENCES `color_types` (`idcolor_type`);

--
-- Ограничения внешнего ключа таблицы `favorite`
--
ALTER TABLE `favorite`
  ADD CONSTRAINT `FKgng78lym9ghokl8n4ge710mcu` FOREIGN KEY (`car_id`) REFERENCES `car` (`idcar`),
  ADD CONSTRAINT `FKh3f2dg11ibnht4fvnmx60jcif` FOREIGN KEY (`user_id`) REFERENCES `user` (`iduser`);

--
-- Ограничения внешнего ключа таблицы `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `FKkjrwwk4ag3bq7rvirdd2mk2eq` FOREIGN KEY (`user_data_id`) REFERENCES `user_data` (`iddata`);

--
-- Ограничения внешнего ключа таблицы `user_data`
--
ALTER TABLE `user_data`
  ADD CONSTRAINT `FK8swoat8jrotg147oumt2jx33y` FOREIGN KEY (`idgender_idgender`) REFERENCES `gender` (`idgender`);

--
-- Ограничения внешнего ключа таблицы `user_role`
--
ALTER TABLE `user_role`
  ADD CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (`user_id`) REFERENCES `user` (`iduser`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

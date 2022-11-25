INSERT INTO airport(icao, city, country, name)
    VALUES ('SEK', 'Sekondi', 'Ghana', 'Sekondi Airport'),
           ('XRC', 'Firsti', 'Tunisia', 'Express Air Cargo'),
           ('ASD', 'Unknown', 'Egypt', 'Air Sinai');

INSERT INTO flight(number, arrival, departure, flighttype, airport_icao)
    VALUES (314, '2022-01-01 12:00:00', '2022-01-01 10:00:00', 0, 'SEK'),
           (271, '2022-01-01 14:00:00', '2022-01-01 12:00:00', 1, 'XRC'),
           (272, '2022-01-01 16:00:00', '2022-01-01 14:00:00', 0, 'SEK'),
           (273, '2022-01-01 18:00:00', '2022-01-01 16:00:00', 0, 'XRC'),
           (274, '2022-01-01 20:00:00', '2022-01-01 18:00:00', 0, 'SEK'),
           (275, '2022-01-01 22:00:00', '2022-01-01 20:00:00', 0, 'XRC'),
           (276, '2022-01-02 00:00:00', '2022-01-01 22:00:00', 0, 'ASD'),
           (277, '2022-01-02 02:00:00', '2022-01-02 00:00:00', 0, 'XRC'),
           (278, '2022-01-02 04:00:00', '2022-01-02 02:00:00', 0, 'SEK'),
           (279, '2022-01-02 06:00:00', '2022-01-02 04:00:00', 1, 'XRC'),
           (280, '2022-01-02 08:00:00', '2022-01-02 06:00:00', 1, 'ASD'),
           (281, '2022-01-02 10:00:00', '2022-01-02 08:00:00', 1, 'XRC'),
           (282, '2022-01-02 12:00:00', '2022-01-02 10:00:00', 1, 'XRC');
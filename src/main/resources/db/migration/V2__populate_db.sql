INSERT INTO client
    (name)
    VALUES
        ('Jeremy'),
        ('James'),
        ('Richard'),
        ('Tom'),
        ('Jerry'),
        ('Michael'),
        ('Ayrton'),
        ('Tony'),
        ('Lara'),
        ('Mark');

INSERT INTO planet
    (id, name)
    VALUES
        ('EARTH', 'Earth'),
        ('MARS', 'Mars'),
        ('MOON', 'Moon'),
        ('VUL', 'Vulkan'),
        ('KRON', 'Kronos'),
        ('ROM', 'Romulus'),
        ('COR1', 'Coruscant'),
        ('END', 'Endor'),
        ('TATOO', 'Tatooine'),
        ('JED', 'Jedha');

INSERT INTO ticket
    (created_at, client_id, from_planet_id, to_planet_id)
    VALUES
        ('2023-04-01 05:10:01.011', 1, 'EARTH', 'MARS'),
        ('2025-05-17 10:23:20.125', 2, 'JED', 'VUL'),
        ('2027-07-19 01:54:55.925', 3, 'COR1', 'EARTH'),
        ('2027-09-22 12:43:10.755', 4, 'MOON', 'EARTH'),
        ('2028-04-30 17:32:41.354', 5, 'TATOO', 'KRON'),
        ('2029-02-05 18:27:37.785', 6, 'ROM', 'END'),
        ('2024-11-08 00:19:29.189', 7, 'MARS', 'MOON'),
        ('2030-12-15 06:01:13.128', 8, 'VUL', 'ROM'),
        ('2025-03-19 18:09:01.598', 9, 'JED', 'COR1'),
        ('2025-10-23 23:58:00.777', 10, 'EARTH', 'END');




-- column_type
INSERT INTO `column_type` (`id`, `name`, `remark`, `gmt_create`, `gmt_modified`, `deleted`)
VALUES (1, '字符', NULL, '2023-03-13 18:27:32', '2023-03-13 18:27:32', 0);
INSERT INTO `column_type` (`id`, `name`, `remark`, `gmt_create`, `gmt_modified`, `deleted`)
VALUES (2, '数值', NULL, '2023-03-13 18:27:32', '2023-03-13 18:27:32', 0);
INSERT INTO `column_type` (`id`, `name`, `remark`, `gmt_create`, `gmt_modified`, `deleted`)
VALUES (3, '小数', NULL, '2023-03-13 18:27:32', '2023-03-13 18:27:32', 0);
INSERT INTO `column_type` (`id`, `name`, `remark`, `gmt_create`, `gmt_modified`, `deleted`)
VALUES (4, '时间', NULL, '2023-03-13 18:27:32', '2023-03-13 18:27:32', 0);

-- custom_table
INSERT INTO `custom_table` (`id`, `name`, `remark`, `gmt_create`, `gmt_modified`, `deleted`)
VALUES (1, '学生表', '记录学生信息', '2023-03-13 18:00:18', '2023-03-13 18:00:18', 0);

-- column_info
INSERT INTO `column_info` (`id`, `custom_table`, `column_type`, `name`, `value`, `remark`, `sort`, `gmt_create`,
                           `gmt_modified`, `deleted`)
VALUES (1, 1, 1, '名称', 'name', NULL, 0, '2023-03-14 14:40:42', '2023-03-14 14:40:42', 0);
INSERT INTO `column_info` (`id`, `custom_table`, `column_type`, `name`, `value`, `remark`, `sort`, `gmt_create`,
                           `gmt_modified`, `deleted`)
VALUES (2, 1, 2, '年龄', 'age', NULL, 0, '2023-03-14 14:40:42', '2023-03-14 14:40:42', 0);

-- custom_table_data
INSERT INTO `custom_table_data` (`id`, `column_info`, `value`, `line`, `gmt_create`, `gmt_modified`, `deleted`)
VALUES (1, 1, 'Alexander', '007d90ec-a8fe-4e74-b76c-6194e25b0dc5', '2023-03-13 18:42:56', '2023-03-13 18:42:56', 0);
INSERT INTO `custom_table_data` (`id`, `column_info`, `value`, `line`, `gmt_create`, `gmt_modified`, `deleted`)
VALUES (2, 2, '18', '007d90ec-a8fe-4e74-b76c-6194e25b0dc5', '2023-03-13 18:42:56', '2023-03-13 18:42:56', 0);
INSERT INTO `custom_table_data` (`id`, `column_info`, `value`, `line`, `gmt_create`, `gmt_modified`, `deleted`)
VALUES (3, 1, 'Alex', '35cf88dc-2293-47d1-9a42-e2d727217a6e', '2023-03-13 18:43:29', '2023-03-13 18:43:29', 0);
INSERT INTO `custom_table_data` (`id`, `column_info`, `value`, `line`, `gmt_create`, `gmt_modified`, `deleted`)
VALUES (4, 2, '24', '35cf88dc-2293-47d1-9a42-e2d727217a6e', '2023-03-13 18:43:29', '2023-03-13 18:43:29', 0);
INSERT INTO `custom_table_data` (`id`, `column_info`, `value`, `line`, `gmt_create`, `gmt_modified`, `deleted`)
VALUES (5, 1, '路飞', '8057f68d-1d38-46cd-9e0b-b50da8ba7ae6', '2023-03-14 16:01:53', '2023-03-14 16:01:53', 0);
INSERT INTO `custom_table_data` (`id`, `column_info`, `value`, `line`, `gmt_create`, `gmt_modified`, `deleted`)
VALUES (6, 2, '25', '8057f68d-1d38-46cd-9e0b-b50da8ba7ae6', '2023-03-14 16:01:59', '2023-03-14 16:01:59', 0);

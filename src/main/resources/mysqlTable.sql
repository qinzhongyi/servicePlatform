ALTER TABLE  sinosoft_user MODIFY COLUMN create_date datetime NOT NULL DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE  sinosoft_shared_buffer MODIFY COLUMN login_date datetime NOT NULL DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE  sinosoft_user_info MODIFY COLUMN create_date datetime NOT NULL DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE  sinosoft_environmental MODIFY COLUMN create_date datetime NOT NULL DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE  sinosoft_project MODIFY COLUMN create_date datetime NOT NULL DEFAULT CURRENT_TIMESTAMP;
INSERT INTO sinosoft_user (user_code, user_name, user_id, user_pwd)
VALUES('admin', 'admin', 'sinosoft_admin', 'admin');

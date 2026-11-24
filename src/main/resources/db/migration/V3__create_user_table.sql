create table TB_USER (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(150) NOT NULL,
    active boolean NOT NULL DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP ON UPDATE CURRENT_TIMESTAMP

);

INSERT INTO tb_user (name, active, password, created_at) VALUES ('Alex', true, '$2a$10$CJjNIlDBbYMM6fRP6TGBJuT5S.IIdzGTArxmusa0kbRg3C275CPd2', now());
INSERT INTO tb_user (name, active, password, created_at) VALUES ('Maria', false, '$2a$10$CJjNIlDBbYMM6fRP6TGBJuT5S.IIdzGTArxmusa0kbRg3C275CPd2', now());

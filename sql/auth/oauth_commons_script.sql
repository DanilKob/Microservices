INSERT INTO oauth_client_details 
   (client_id, client_secret, scope, authorized_grant_types, 
   authorities, access_token_validity, refresh_token_validity)
VALUES
   ('resource-server-api', '$2a$10$zokLfCnsFRAfb3g.KB9d3OEQwinz510kdwIRoebAS3yMV2PLnXdEq', 'webclient",mobileclient', 'password,refresh_token', 
   'ROLE_CLIENT,ROLE_TRUSTED_CLIENT', 900, 2592000);
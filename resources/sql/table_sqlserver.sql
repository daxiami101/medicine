SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[system_info]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[system_info](
	[id] [varchar](32) NOT NULL,
	[conf_type] [varchar](20) NOT NULL,
	[user_id] [varchar](32) NOT NULL,
	[json_str] [varchar](4000) NOT NULL,
 CONSTRAINT [PK_system_info] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (IGNORE_DUP_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
END
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[acl_resource]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[acl_resource](
	[id] [varchar](32) NOT NULL,
	[list] [int] NOT NULL,
	[logo_pic] [varchar](100) NULL,
	[menu_id] [varchar](32) NULL,
	[menu_type] [varchar](10) NOT NULL,
	[name] [varchar](200) NOT NULL,
	[type_code] [varchar](20) NOT NULL,
	[url] [varchar](300) NOT NULL,
	[request_method] [varchar](50) NOT NULL,
 CONSTRAINT [PK_acl_resource] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (IGNORE_DUP_KEY = OFF) ON [PRIMARY],
 CONSTRAINT [IX_acl_resource] UNIQUE NONCLUSTERED 
(
	[url] ASC,
	[request_method] ASC
)WITH (IGNORE_DUP_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
END
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[acl_role]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[acl_role](
	[id] [varchar](32) NOT NULL,
	[name] [varchar](100) NOT NULL,
	[list] [int] NOT NULL,
	[system] [int] NOT NULL,
	[info] [varchar](300) NULL,
 CONSTRAINT [PK_acl_role] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (IGNORE_DUP_KEY = OFF) ON [PRIMARY],
 CONSTRAINT [IX_acl_role] UNIQUE NONCLUSTERED 
(
	[name] ASC
)WITH (IGNORE_DUP_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
END
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[system_log]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[system_log](
	[id] [varchar](32) NOT NULL,
	[ip] [varchar](50) NOT NULL,
	[log_type] [varchar](50) NOT NULL,
	[optime] [datetime] NOT NULL,
	[user_id] [varchar](32) NOT NULL,
	[info] [varchar](300) NOT NULL,
 CONSTRAINT [PK_system_log] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (IGNORE_DUP_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
END
GO

IF NOT EXISTS (SELECT * FROM sys.indexes WHERE object_id = OBJECT_ID(N'[dbo].[system_log]') AND name = N'IX_system_log')
CREATE NONCLUSTERED INDEX [IX_system_log] ON [dbo].[system_log] 
(
	[optime] ASC
)WITH (IGNORE_DUP_KEY = OFF) ON [PRIMARY]
GO

IF NOT EXISTS (SELECT * FROM sys.indexes WHERE object_id = OBJECT_ID(N'[dbo].[system_log]') AND name = N'IX_system_log_1')
CREATE NONCLUSTERED INDEX [IX_system_log_1] ON [dbo].[system_log] 
(
	[log_type] ASC
)WITH (IGNORE_DUP_KEY = OFF) ON [PRIMARY]
GO

IF NOT EXISTS (SELECT * FROM sys.indexes WHERE object_id = OBJECT_ID(N'[dbo].[system_log]') AND name = N'IX_system_log_2')
CREATE NONCLUSTERED INDEX [IX_system_log_2] ON [dbo].[system_log] 
(
	[user_id] ASC
)WITH (IGNORE_DUP_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[acl_role_resource]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[acl_role_resource](
	[id] [varchar](32) NOT NULL,
	[role_id] [varchar](32) NOT NULL,
	[resource_id] [varchar](32) NOT NULL,
 CONSTRAINT [PK_acl_role_resource] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (IGNORE_DUP_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
END
GO

IF NOT EXISTS (SELECT * FROM sys.indexes WHERE object_id = OBJECT_ID(N'[dbo].[acl_role_resource]') AND name = N'IX_acl_role_resource')
CREATE NONCLUSTERED INDEX [IX_acl_role_resource] ON [dbo].[acl_role_resource] 
(
	[resource_id] ASC
)WITH (IGNORE_DUP_KEY = OFF) ON [PRIMARY]
GO

IF NOT EXISTS (SELECT * FROM sys.indexes WHERE object_id = OBJECT_ID(N'[dbo].[acl_role_resource]') AND name = N'IX_acl_role_resource_1')
CREATE NONCLUSTERED INDEX [IX_acl_role_resource_1] ON [dbo].[acl_role_resource] 
(
	[role_id] ASC
)WITH (IGNORE_DUP_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[acl_user]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[acl_user](
	[id] [varchar](32) NOT NULL,
	[email] [varchar](200) NULL,
	[fax] [varchar](50) NULL,
	[login_name] [varchar](200) NOT NULL,
	[male] [int] NOT NULL,
	[mobile] [varchar](50) NULL,
	[name] [varchar](200) NOT NULL,
	[name_py] [varchar](200) NOT NULL,
	[passwd] [varchar](512) NOT NULL,
	[status] [varchar](50) NOT NULL,
	[tel] [varchar](50) NULL,
	[role_id] [varchar](32) NOT NULL,
 CONSTRAINT [PK_acl_user] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (IGNORE_DUP_KEY = OFF) ON [PRIMARY],
 CONSTRAINT [IX_acl_user_1] UNIQUE NONCLUSTERED 
(
	[login_name] ASC
)WITH (IGNORE_DUP_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
END
GO

IF NOT EXISTS (SELECT * FROM sys.indexes WHERE object_id = OBJECT_ID(N'[dbo].[acl_user]') AND name = N'IX_acl_user')
CREATE NONCLUSTERED INDEX [IX_acl_user] ON [dbo].[acl_user] 
(
	[name_py] ASC
)WITH (IGNORE_DUP_KEY = OFF) ON [PRIMARY]
GO

IF NOT EXISTS (SELECT * FROM sys.indexes WHERE object_id = OBJECT_ID(N'[dbo].[acl_user]') AND name = N'IX_acl_user_2')
CREATE NONCLUSTERED INDEX [IX_acl_user_2] ON [dbo].[acl_user] 
(
	[name] ASC
)WITH (IGNORE_DUP_KEY = OFF) ON [PRIMARY]
GO

IF NOT EXISTS (SELECT * FROM sys.indexes WHERE object_id = OBJECT_ID(N'[dbo].[acl_user]') AND name = N'IX_acl_user_3')
CREATE NONCLUSTERED INDEX [IX_acl_user_3] ON [dbo].[acl_user] 
(
	[role_id] ASC
)WITH (IGNORE_DUP_KEY = OFF) ON [PRIMARY]
GO
IF NOT EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_system_log_acl_user]') AND parent_object_id = OBJECT_ID(N'[dbo].[system_log]'))
ALTER TABLE [dbo].[system_log]  WITH CHECK ADD  CONSTRAINT [FK_system_log_acl_user] FOREIGN KEY([user_id])
REFERENCES [dbo].[acl_user] ([id])
GO
IF NOT EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_acl_role_resource_acl_resource]') AND parent_object_id = OBJECT_ID(N'[dbo].[acl_role_resource]'))
ALTER TABLE [dbo].[acl_role_resource]  WITH CHECK ADD  CONSTRAINT [FK_acl_role_resource_acl_resource] FOREIGN KEY([resource_id])
REFERENCES [dbo].[acl_resource] ([id])
GO
IF NOT EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_acl_role_resource_acl_role]') AND parent_object_id = OBJECT_ID(N'[dbo].[acl_role_resource]'))
ALTER TABLE [dbo].[acl_role_resource]  WITH CHECK ADD  CONSTRAINT [FK_acl_role_resource_acl_role] FOREIGN KEY([role_id])
REFERENCES [dbo].[acl_role] ([id])
GO
IF NOT EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_acl_user_acl_role]') AND parent_object_id = OBJECT_ID(N'[dbo].[acl_user]'))
ALTER TABLE [dbo].[acl_user]  WITH CHECK ADD  CONSTRAINT [FK_acl_user_acl_role] FOREIGN KEY([role_id])
REFERENCES [dbo].[acl_role] ([id])

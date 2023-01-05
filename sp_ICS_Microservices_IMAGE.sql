USE [fisdb_bdv]
GO

/****** Object:  StoredProcedure [dbo].[sp_ICS_Microservices_IMAGE]    Script Date: 3/12/2022 8:11:57 p. m. ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO



CREATE PROCEDURE [dbo].[sp_ICS_Microservices_IMAGE]
	@Accion varchar(6) = NULL,			-- Comando INSERT, DELETE, SELECT, ALL
	@iImagen numeric(18,0) = NULL,			-- Codigo de la Imagen
	@iTipoImagen smallint = NULL,			-- Nombre del grupo
	@Imagen	image = NULL,			-- Permiso del grupo
	@Salida numeric(18,0)  OUTPUT
--	@sUser varchar(10) = NULL			-- Usuario quien ejecuta la operacion
--	WITH ENCRYPTION
	as 
-- Chequea que la accion sea valida
	if ( @Accion is NULL )
	begin
		RAISERROR('Accion no puede estar en Blanco',16,-1) 
		goto ETIQUETA_RETORNO
	end
-- Declaracion de variables
	DECLARE @logid  integer,
 		@today  datetime,
  		@sImagen  varchar(20) 

/*--------------------------------*/
/* Accion INSERT                  */
/*--------------------------------*/
 	if ( @Accion = 'INSERT' )	
	begin
-- Chequea que el tip de imagen es valido
		if ( @iTipoImagen is NULL )		
		begin
			RAISERROR('El tipo de imagen no puede estar en Blanco',16,-1) 
			goto ETIQUETA_RETORNO
		end
-- Chequea que la iamgen sea valida
		if ( @Imagen is NULL )		
		begin
			RAISERROR('La imagen no puede estar en Blanco',16,-1) 
			goto ETIQUETA_RETORNO
		end
-- Chequea que el tipo de imagen exista en la tabla TipoImagen
 
  		if not exists (SELECT * FROM TipoImagen WHERE ID_TipoImagen = @iTipoImagen) 
		begin 
			RAISERROR('Tipo de imagen no esta registrado en la base de datos',16,-1) 
			goto ETIQUETA_RETORNO
		end	
		 
-- Inicio del proceso de insercion o actualizacion de un grupo

    	INSERT INTO Imagen(ID_TipoImagen, Imagen) VALUES
		(	
			@iTipoImagen,
			@Imagen
		)
		--SELECT @@Identity AS idImagen
		SET @Salida = @@Identity 
		--Return @IDIMagen

	end



ETIQUETA_RETORNO:

GO



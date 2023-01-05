USE [fisdb_bdv]
GO

/****** Object:  StoredProcedure [dbo].[sp_ICS_Microservices_FIRMANTE]    Script Date: 3/12/2022 8:10:45 p. m. ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO






CREATE PROCEDURE [dbo].[sp_ICS_Microservices_FIRMANTE]
	@Accion varchar(6) = NULL,			    -- Comando INSERT, DELETE, SELECT, ALL
	@iImagen numeric(18,0) = NULL,			-- Codigo de la Imagen
	@sProductoId varchar(20) = NULL, 	    -- Cuenta
	@sPersonaId	varchar(18) = NULL,	        -- ID de la persona
	@iTipo  smallint = NULL,		-- Tipo del autorizado
	@Imagen	image = NULL			        -- Permiso del grupo
--	@sUser varchar(10) = NULL			    -- Usuario quien ejecuta la operacion
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
-- Chequea que la cuenta sea valida
		if ( @sProductoId is NULL )
		begin
			RAISERROR('El producto no puede estar en Blanco',16,-1) 
			goto ETIQUETA_RETORNO
		end
 -- Chequea que la persona sea valida
 
		if ( @sPersonaId is NULL )
		begin
			RAISERROR('La persona no puede estar en Blanco',16,-1) 
			goto ETIQUETA_RETORNO
		end
-- Chequea que el tipo de la persona sea valido
		if ( @iTipo is NULL )
		begin
			RAISERROR('La Tipo de autorizado no puede estar en Blanco',16,-1) 
			goto ETIQUETA_RETORNO
		end
-- Chequea que la iamgen sea valida
		if ( @Imagen is NULL )		
		begin
			RAISERROR('La imagen no puede estar en Blanco',16,-1) 
			goto ETIQUETA_RETORNO
		end
-- Chequea que el tipo de imagen exista en la tabla TipoImagen
 
		 
-- Inicio del proceso de insercion o actualizacion de un grupo

    	INSERT INTO Imagen(ID_TipoImagen, Imagen) VALUES
		(	
			1,
			@Imagen
		)
		--SELECT @@Identity AS idImagen
		SET @iImagen = @@Identity 

		if not exists (SELECT * FROM Producto_Persona WHERE ID_Cuenta = @sProductoId AND ID_Persona = @sPersonaId) 
  		begin 
				INSERT INTO Producto_Persona VALUES
				( 
					@sProductoId, 
					@sPersonaId, 
					@iTipo, 
					@iImagen
				)
  	    end
		else
		begin
		    UPDATE Producto_Persona SET ID_Tipo=@iTipo, ID_Imagen=@iImagen  where ID_Cuenta = @sProductoId AND ID_Persona = @sPersonaId
		end
		goto ETIQUETA_RETORNO

	end



ETIQUETA_RETORNO:

GO



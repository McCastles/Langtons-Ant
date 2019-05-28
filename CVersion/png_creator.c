#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <stdarg.h>

#include "headers/png_creator.h"
#include <png.h>

int x, y;
png_byte color_type;
png_byte bit_depth;

png_structp png_ptr;
png_infop info_ptr;
int number_of_passes;
png_bytep * row_pointers;

void write_png_file(char* file_name, int width, int height) 
{
  FILE *fp = fopen(file_name, "wb");
  if (!fp)
    printf("[write_png_file] File %s could not be opened for writing", file_name);
  png_ptr = png_create_write_struct(PNG_LIBPNG_VER_STRING, NULL, NULL, NULL);
  if (!png_ptr)
    printf("[write_png_file] png_create_write_struct failed");
  info_ptr = png_create_info_struct(png_ptr);
  if (!info_ptr)
    printf("[write_png_file] png_create_info_struct failed");
  if (setjmp(png_jmpbuf(png_ptr)))
    printf("[write_png_file] Error during init_io");
  png_init_io(png_ptr, fp);
  if (setjmp(png_jmpbuf(png_ptr)))
    printf("[write_png_file] Error during writing header");
  png_set_IHDR(png_ptr, info_ptr, width, height,
   bit_depth, color_type, PNG_INTERLACE_NONE,
   PNG_COMPRESSION_TYPE_BASE, PNG_FILTER_TYPE_BASE);
  png_write_info(png_ptr, info_ptr);
  if (setjmp(png_jmpbuf(png_ptr)))
    printf("[write_png_file] Error during writing bytes");
  png_write_image(png_ptr, row_pointers);
  if (setjmp(png_jmpbuf(png_ptr)))
    printf("[write_png_file] Error during end of write");
  png_write_end(png_ptr, NULL);
  for (y=0; y<height; y++)
    free(row_pointers[y]);
  free(row_pointers);
  fclose(fp);
}



void process_file(board_t b) 
{
	bit_depth = 8;
	color_type = PNG_COLOR_TYPE_GRAY;

	number_of_passes = 7;
	row_pointers = (png_bytep*) malloc(sizeof(png_bytep) * b->h);
	
	for (y=0; y < (b->h); y++)
		row_pointers[y] = (png_byte*) malloc(sizeof(png_byte) * b->w);

	for (y = 0; y < (b->h); y++) 
	{
		png_byte* row = row_pointers[y];
		for (x = 0; x < (b->w); x++) 
		{
			if (b->board[x][y] == 0)
      			row[x] = 255;
			else
				row[x] = 0;
      		//printf("Pixel at position [ %d - %d ] has RGBA values: %d\n", x, y, row[x]);
    	}
		
		
  	}
}




















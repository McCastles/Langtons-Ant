#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <stdarg.h>

#include "headers/data_reciever.h"
#include "headers/ant.h"
#include "headers/png_creator.h"
#include "headers/board.h"

int main(int argc, char **argv)
{
	/* Initial check for arguments  */
    if (argc != 2)
    {
        printf("Please, run the program again with the config file as an argument.\nFor instance: ./ant src/config1.txt\n");
        return 1;
  	}

	char* source = argv[1];
	
	/* Reading configurations from a config file  */
	int steps = readValueFromFile(source,"STEPS");
	int width = readValueFromFile(source,"WIDTH"); 
	int height = readValueFromFile(source,"HEIGHT"); 
	int ants_number = readValueFromFile(source, "ANTS_NUMBER");

	/* Creating the board and the ants' array  */
	board_t board = createBoard(height, width);
	ant_t* ant_array = malloc(ants_number * sizeof(ant_t));

	/* Setting up for extracting certain configurations */
	char* postfix_ant = "_ANT";
	char* postfix_x = "_START_X";
	char* postfix_y = "_START_Y";
	char* postfix_pos = "_START_POS";	

	for (int i = 0; i < ants_number; i++)
	{		
		/* Generating parameter's name for automatic capture */
		char* current_ant = malloc(16);
		char* current_x = malloc(16);
		char* current_y = malloc(16);
		char* current_pos = malloc(16);
		
		sprintf(current_ant, "%d", (i+1));
		sprintf(current_x, "%d", (i+1));
		sprintf(current_y, "%d", (i+1));
		sprintf(current_pos, "%d", (i+1));

		/* Creating the following ant and add it to the array */
		ant_array[i] = createAnt ( 
			width, height, source,
			strcat(current_ant, postfix_ant), 
			strcat(current_x, postfix_x),
			strcat(current_y, postfix_y),
			strcat(current_pos, postfix_pos) );
	
		/* Show log  */
		if (ant_array[i] != NULL)
        	printf("Ant #%d created\n", i+1);
   		else
        	printf("Ant #%d WAS NOT created\n", i+1);
	}

	
	/* Main cycle: repeatedly move ants if alive */
	for (int j = 0; j < steps; j++)
		for (int i = 0; i < ants_number; i++)
			if ( (ant_array[i] != NULL) && (ant_array[i]->isAlive != 0) )
				antStep(ant_array[i], board);
	
	/* The function was used during development */
	// printBoard(board);
	
	/* Create .png file and print the board to it */
	process_file(board);
	write_png_file("out.png", width, height);
	
	return 0;
}














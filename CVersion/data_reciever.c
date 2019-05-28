#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "headers/data_reciever.h"

/* Checks if the position parameter has the allowed value */
int check_pos(char* src, char* param)
{
    int check = readValueFromFile(src, param);
    if ((check!=0)&&(check!=1)&&(check!=2)&&(check!=3))
        {
            printf("Wrong %s parameter in config.txt\n", param);
            return -1;
        }
    return check;
}

/* Checks if the x parameter has the allowed value  */   
int check_x(int width, char* src, char* param)
{
	int check = readValueFromFile(src, param);
    if (check >= width)
		{
			printf("Wrong %s parameter in config.txt\n", param);
			return -1;
		}
	else 
		return check;
}

/* Checks if the y parameter has the allowed value  */   
int check_y(int height, char* src, char* param)
{
    int check = readValueFromFile(src, param);
    if (check >= height)
        {
            printf("Wrong %s parameter in config.txt\n", param);
			return -1;
        }
    else 
        return check;
}

/* Reads value from the config file */ 
int readValueFromFile(char* src, char* param)
{
	FILE* in = fopen(src, "r");
	if (in == NULL)
	{
		printf("Failed to open file: %s\nNOTE: All config files are placed inside src/ directory\n", src);
		exit(2);
	}

	int result = 0;

    char bin [16];
    while(fscanf(in, "%s", bin) != EOF)
    	if (strcmp(bin, param) == 0)
        { 
		    fscanf(in, "%d", &result);
			fclose(in);
			return result;
		}	
	fclose(in);
}






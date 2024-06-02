import { AfterViewInit, ChangeDetectorRef, Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator,  } from '@angular/material/paginator';

import { UsuarioService } from './core/servicios/usuario.service';
import { RegisterUserDTO } from 'juliaositembackenexpress/src/api/dtos/RegisterUserDTO';
import { Subscription } from 'rxjs';
import { PlantillaResponse } from 'juliaositembackenexpress/src/utils/PlantillaResponse';
import { RouterOutlet } from '@angular/router';

import { CoreModule } from './core/core.module';
import { RegisterUserFormComponent } from './register-user-form/register-user-form.component';
import { MatDialog } from '@angular/material/dialog';
import { MatGridTile } from '@angular/material/grid-list';



@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  standalone: true,
  imports: [RouterOutlet,CoreModule,RegisterUserFormComponent],
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit, AfterViewInit, OnDestroy {



  rowSpam =4;
  usersResponse: PlantillaResponse<RegisterUserDTO> = {
    dataList: [],
  };
  displayedColumns: string[] = ['Shared',"idBussines", 'name', 'email', 'phone'];
  dataSource = new MatTableDataSource<RegisterUserDTO>(this.usersResponse.dataList); // Inicializar con una matriz vacía

 dataSize :number =0;
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatGridTile) gridTile!: MatGridTile; // Referencia al MatGridTile

   usersSubscription: Subscription | undefined;

  constructor(private usuarioService: UsuarioService,private dialog: MatDialog,private cdr: ChangeDetectorRef) {}

  ngOnInit(): void {
    this.getAllUsers();
    this.dataSource.paginator = this.paginator;
    if(this.dataSize > 3 ){
        let size = this.dataSize - this.rowSpam
        this.rowSpam= this.rowSpam + size 
    } 
  }

  
  ngAfterViewInit(): void {
    
  }

  getAllUsers(id?: string | null, idBussines?: number | null): void {
    this.usersSubscription = this.usuarioService.all(id, idBussines).subscribe({
      next: (response) => {
        this.usersResponse = response;
        console.log(this.usersResponse)
        this.dataSource.data = response?.dataList ?? [];
       this.dataSize= this.dataSource.data.length;
      },
      error: (error) => {
        console.error(error);
      }
    });
  }

  applyFilter(filterValue: string): void {
    this.dataSource.filter = filterValue.trim().toLowerCase();

    
  }
  ngOnDestroy(): void {
    this.usersSubscription?.unsubscribe();
  }


  applyFilterByidBussines(idBussines: string) {
    this.getAllUsers("",parseInt(idBussines))
    }
    
  openRegisterForm(): void {
    const dialogRef = this.dialog.open(RegisterUserFormComponent, {
      width: '400px'
    });

    dialogRef.afterClosed().subscribe(result => {
        console.log(result)
      if (result === true) {
        let size=this.dataSize;
        this.getAllUsers();
        if(size < this.dataSource.data.length) 
           this.rowSpam ++;
        
      }
    });
  }
}

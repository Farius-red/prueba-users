import { AfterViewInit, Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { UsuarioService } from './core/servicios/usuario.service';
import { RegisterUserDTO } from 'juliaositembackenexpress/src/api/dtos/RegisterUserDTO';
import { Subscription } from 'rxjs';
import { PlantillaResponse } from 'juliaositembackenexpress/src/utils/PlantillaResponse';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  standalone: true,
  imports: [RouterOutlet, MatTableModule, MatPaginatorModule],
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit, AfterViewInit, OnDestroy {
  title = 'users';
  usersResponse: PlantillaResponse<RegisterUserDTO> = {
    dataList: [],
  };
  displayedColumns: string[] = ['position', 'name', 'weight', 'symbol'];
  dataSource = new MatTableDataSource<RegisterUserDTO>(this.usersResponse.dataList); // Inicializar con una matriz vacía


  @ViewChild(MatPaginator) paginator!: MatPaginator;
   usersSubscription: Subscription | undefined;

  constructor(private usuarioService: UsuarioService) {}

  ngOnInit(): void {
    this.getAllUsers("", 1);
    this.dataSource.paginator = this.paginator;
  }

  ngAfterViewInit(): void {
    
  }

  getAllUsers(id?: string | null, idBussines?: number | null): void {
    this.usersSubscription = this.usuarioService.all(id, idBussines).subscribe({
      next: (response) => {
        this.usersResponse = response;
        console.log(this.usersResponse)
        this.dataSource.data = response?.dataList ?? [];

      },
      error: (error) => {
        console.error(error);
      }
    });
  }

  ngOnDestroy(): void {
    this.usersSubscription?.unsubscribe();
  }
}

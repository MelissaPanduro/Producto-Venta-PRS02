import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Issue } from './model/issue';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { IssueService } from './service/issue.service';
@Component({
  standalone: true,
  imports: [CommonModule , HttpClientModule, FormsModule],
  templateUrl: './defer-options.component.html',
  styles: ``
})
export class DeferOptionsComponent implements OnInit {

  isModalOpen = false;
  issue: Issue[] = [];
  filteredIssues: Issue[] = [];
  isLoading: boolean = true;
  isActive: boolean = true;
  isEditMode: boolean = false; // Para determinar si estamos en modo edición o agregar

  // Filtros
  nameFilter: string = '';
  descriptionFilter: string = '';

    // Información del proveedor
    editSupplier: Issue | null = null;
    issueForm: Issue = { id: 0, name: '', workshopId: 0, scheduledTime: '', state: 'A'};


  constructor(private issueService: IssueService) {}

  ngOnInit(): void {
    this.getIssues();
  }

  getIssues(): void {
    this.issueService.getIssues().subscribe({
      next: (data) => {
        this.issue = data;
        this.filterIssues();
        this.isLoading = false;
      },
      error: (err) => {
        console.error('Error fetching issues:', err);
        this.isLoading = false;
      }
    });
  }

    // Filtrar proveedores según estado, nombre y descripción
    filterIssues(): void {
      this.filteredIssues = this.issue.filter(issue => {
        const matchesStatus = issue.state === (this.isActive ? 'A' : 'I');
        const matchesName = issue.name
          .toLowerCase()
          .includes(this.nameFilter.toLowerCase());
        return matchesStatus && matchesName;
      });
    }

      // Cambiar el estado del switcher y actualizar la lista filtrada
  toggleStatus(): void {
    this.filterIssues(); // Refrescar la lista filtrada
  }

  // Activar un proveedor
  activateIssue(id: number | undefined): void {
    if (id !== undefined) {
      this.issueService.activateIssue(id).subscribe({
        next: () => {
          this.getIssues();
        },
        error: (err) => {
          console.error('Error activating supplier:', err);
        }
      });
    } else {
      console.error('Invalid supplier ID');
    }
  }

  // Inactivar un proveedor
  inactivateIssue(id: number | undefined): void {
    if (id !== undefined) {
      this.issueService.deactivateIssue(id).subscribe({
        next: () => {
          this.getIssues();
        },
        error: (err) => {
          console.error('Error inactivating supplier:', err);
        }
      });
    } else {
      console.error('Invalid supplier ID');
    }
  }

  // Abrir el modal en modo agregar
  openModal(): void {
    this.isEditMode = false;
    this.issueForm = { id: 0, name: '', workshopId: 0, scheduledTime: '', state: 'A'};
    this.isModalOpen = true;
  }

  // Abrir el modal en modo edición
  editSupplierDetails(issue: Issue): void {
    this.isEditMode = true;
    this.issueForm = { ...issue };
    this.isModalOpen = true;
  }

  // Cerrar el modal
  closeModal(): void {
    this.isModalOpen = false;
  }

  // Agregar un nuevo proveedor
  addIssue(): void {
    // Asegurarse de que el id esté como 0 antes de la creación
    if (this.issueForm.id === 0) {
      this.issueForm.id = undefined; // O eliminar la propiedad id
    }

    this.issueService.createIssue(this.issueForm).subscribe({
      next: () => {
        this.getIssues(); // Refrescar la lista
        this.closeModal(); // Cerrar el modal
      },
      error: (err) => {
        console.error('Error adding supplier:', err);
      }
    });
  }

  // Actualizar un proveedor existente
  updateSupplier(): void {
    if (this.issueForm.id) {
      this.issueService.updateIssue(this.issueForm.id, this.issueForm).subscribe({
        next: () => {
          this.getIssues(); // Refrescar la lista
          this.closeModal(); // Cerrar el modal
        },
        error: (err) => {
          console.error('Error updating supplier:', err);
        }
      });
    }
  }
}
